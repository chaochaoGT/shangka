package com.geek.shengka.gold.service.impl;

import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.basemodel.ResponseCode;
import com.geek.shengka.common.cache.CacheProvider;
import com.geek.shengka.common.enums.TradeTypeEnum;
import com.geek.shengka.gold.entity.SkTreasureBoxConfig;
import com.geek.shengka.gold.entity.SkUserTreasureBoxRecord;
import com.geek.shengka.gold.mapper.SkTreasureBoxConfigDAO;
import com.geek.shengka.gold.mapper.SkUserTreasureBoxRecordDAO;
import com.geek.shengka.gold.rabbitmq.RabbitmqSender;
import com.geek.shengka.gold.request.MqCoinMsg;
import com.geek.shengka.gold.request.OpenBoxRequest;
import com.geek.shengka.gold.request.TreasureBoxRequest;
import com.geek.shengka.gold.response.GetCoinResponse;
import com.geek.shengka.gold.response.TreasureBoxConfigResponse;
import com.geek.shengka.gold.response.UserTreasureBoxResponse;
import com.geek.shengka.gold.service.SkTreasureBoxService;
import com.geek.shengka.gold.util.DateFormatUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 宝箱业务实现
 *
 * @author: yunfei
 * @create: 2019-08-01 15:04
 **/
@Service
public class SkTreasureBoxServiceImpl implements SkTreasureBoxService {

    private final static Logger LOGGER = LoggerFactory.getLogger(SkTreasureBoxServiceImpl.class);

    @Autowired
    private SkTreasureBoxConfigDAO skTreasureBoxConfigDAO;

    @Autowired
    private SkUserTreasureBoxRecordDAO skUserTreasureBoxRecordDAO;

    @Autowired
    private RedisManageServiceImpl redisManageService;

    @Autowired
    private RabbitmqSender rabbitmqSender;


    /**
     * 用户剩余次数缓存key
     */
    private static final String USER_TREASURE_BOX_LIMIT = "sk:user:treasureBoxLimit:";
    private static final Long USER_TREASURE_BOX_EXPIRE = 60 * 1000L * 30;

    private static final String USER_TREASURE_BOX_OPEN="sk:user:openTreasureBox:%s";


    @Override
    public TreasureBoxConfigResponse config(Long userId) {
        SkTreasureBoxConfig response = skTreasureBoxConfigDAO.selectConfig();
        if(response==null){
            return null;
        }
        TreasureBoxConfigResponse configResponse = new TreasureBoxConfigResponse();
        configResponse.setWatchDuration(response.getWatchDuration());
        configResponse.setConfigid(response.getId());
        configResponse.setIconUrl(response.getIconUrl());
        configResponse.setLimitType(response.getLimitType().intValue());
        configResponse.setLimit(getUserLimit(userId, response));
        return configResponse;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse open(OpenBoxRequest params) {
        if (params.getUserId() <= 0) {
            return BaseResponse.newFailure(ResponseCode.REQUIRED_PARAMS_MISSING);
        }
        if(StringUtils.isNotBlank(CacheProvider.get(String.format(USER_TREASURE_BOX_OPEN,params.getUserId())))){
            return BaseResponse.newFailure(ResponseCode.FREQUENTLY);
        }
        SkTreasureBoxConfig config = skTreasureBoxConfigDAO.selectByPrimaryKey(params.getConfigId());
        if (config == null) {
            return BaseResponse.newFailure(ResponseCode.BOX_IS_NOT_EXIST);
        }
        CacheProvider.set(String.format(USER_TREASURE_BOX_OPEN,params.getUserId()),params.getUserId().toString(),(config.getWatchDuration()-3)*1000);
        if (config.getEnable().intValue()==1) {
            return BaseResponse.newFailure(ResponseCode.BOX_IS_NOT_EXIST);
        }
        if (getUserLimit(params.getUserId(), config) <= 0) {
            return BaseResponse.newFailure(ResponseCode.BOX_GET_LIMIT);
        }
        SkUserTreasureBoxRecord record = new SkUserTreasureBoxRecord();
        record.setUserId(params.getUserId());
        record.setTreasureId(config.getId().toString());
        BigDecimal coin = getUserCoin(config);
        record.setGoldCoinWin(coin);
        record.setState((byte) 0);
        record.setHandleState((byte) 0);
        record.setOrderNo(redisManageService.getOrderNo());
        skUserTreasureBoxRecordDAO.insertSelective(record);
        addUserCoinCache(params.getUserId(), coin, config.getLimitType().intValue());
        return BaseResponse.newSuccess(GetCoinResponse.builder().coin(record.getGoldCoinWin().intValue()).recordId(record.getId()).build());
    }

    /***
     * 开启宝箱，更新用户领取金币上线缓存
     * @param userId
     * @param coin
     * @param type
     */
    private final void addUserCoinCache(Long userId, BigDecimal coin, int type) {
        int num = 1;
        if (type == 2) {
            num = coin.intValue();
        }
        redisManageService.incUserValue(USER_TREASURE_BOX_LIMIT + type + ":", userId, -num, USER_TREASURE_BOX_EXPIRE);
    }

    @Override
    public int getCoin(TreasureBoxRequest params) {
        SkUserTreasureBoxRecord record = skUserTreasureBoxRecordDAO.selectByPrimaryKey(params.getRecordId());
        if (record == null || !record.getUserId().equals(params.getUserId())) {
            LOGGER.warn("treasureBox is xistent get user " + params.getUserId());
            return 0;
        }
        if (record.getState() == 0) {
            record.setState((byte) 1);
            skUserTreasureBoxRecordDAO.updateByPrimaryKeySelective(record);
            MqCoinMsg msg = new MqCoinMsg();
            msg.setUserId(params.getUserId());
            msg.setGoldCoin(record.getGoldCoinWin());
            msg.setOrderNo(record.getOrderNo());
            msg.setTradeTypeCode(TradeTypeEnum.OPEN_TREASURE.getCode());
            rabbitmqSender.sendCoinMessage(msg);
        }
        return record.getGoldCoinWin().intValue();
    }

    @Override
    public List<UserTreasureBoxResponse> noGetList(Long userId) {
        Date dateNow = new Date();
        String createTimeStart = DateFormatUtil.startDate(dateNow);
        String createTimeEnd = DateFormatUtil.endDate(dateNow);
        List<SkUserTreasureBoxRecord> list = skUserTreasureBoxRecordDAO.selectUserRecordByDay(userId, createTimeStart, createTimeEnd, "0");
        return list.stream().map(record -> translation(record)).collect(Collectors.toList());
    }

    /***
     * 转化
     * @param record
     * @return
     */
    private UserTreasureBoxResponse translation(SkUserTreasureBoxRecord record) {
        UserTreasureBoxResponse userTreasureBoxResponse = new UserTreasureBoxResponse();
        userTreasureBoxResponse.setRecordId(record.getId());
        userTreasureBoxResponse.setGoldCoinWin(record.getGoldCoinWin());
        return userTreasureBoxResponse;
    }

    /**
     * 获取开启宝箱金币数
     *
     * @param config
     * @return
     */
    private BigDecimal getUserCoin(SkTreasureBoxConfig config) {
        config.getCoinMin();
        int coin = new Random().nextInt(config.getCoinMax());
        coin = coin <= config.getCoinMin() ? config.getCoinMin() : coin;
        return BigDecimal.valueOf(coin);
    }

    public static void main(String[] args) {
        int min=10;
        Random random = new Random();
        int coin = random.nextInt(50);
        coin = coin <= min ? min : coin;
        System.out.println(coin);
    }

    /**
     * 获取剩余次数 或额度
     *
     * @param userId
     * @param config   type       1是次数，2是额度
     * @return
     */
    private final Integer getUserLimit(Long userId, SkTreasureBoxConfig config) {
        int type = config.getLimitType().intValue();
        Integer limitTotal = type==1?config.getLimitCount():config.getLimitCoinAmount();
        if (userId < 0) {
            return limitTotal;
        }
        String limit = redisManageService.getUserValue(USER_TREASURE_BOX_LIMIT + type + ":", userId);
        if (StringUtils.isNotBlank(limit)) {
            return Integer.parseInt(limit);
        }
        int sum = 0;
        Date dateNow = new Date();
        String createTimeStart = DateFormatUtil.startDate(dateNow);
        String createTimeEnd = DateFormatUtil.endDate(dateNow);
        List<SkUserTreasureBoxRecord> list = skUserTreasureBoxRecordDAO.selectUserRecordByDay(userId, createTimeStart, createTimeEnd, null);
        switch (type) {
            case 1:
                sum = list.size();
                break;
            case 2:
                sum = list.stream().mapToInt(value -> value.getGoldCoinWin().intValue()).sum();
                break;
            default:
                break;
        }
        int count = (limitTotal - sum) <= 0 ? 0 : limitTotal - sum;
        redisManageService.setUserValue(USER_TREASURE_BOX_LIMIT + type + ":", userId, count, USER_TREASURE_BOX_EXPIRE);
        return count;
    }

}
