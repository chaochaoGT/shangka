package com.geek.shengka.user.service.impl;

import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.basemodel.ResponseCode;
import com.geek.shengka.common.enums.UserBaseTypeEnum;
import com.geek.shengka.common.mq.NoticeEvent;
import com.geek.shengka.common.mq.UserBaseDataMsg;
import com.geek.shengka.user.constant.UserConstant;
import com.geek.shengka.user.entity.SkFans;
import com.geek.shengka.user.entity.vo.SkFansVO;
import com.geek.shengka.user.mapper.SkFansDAO;
import com.geek.shengka.user.rabbitmq.RabbitmqSender;
import com.geek.shengka.user.rabbitmq.entity.TaskEvent;
import com.geek.shengka.user.request.AttentionRequest;
import com.geek.shengka.user.request.FansRequest;
import com.geek.shengka.user.service.SkFansService;
import com.geek.shengka.user.utils.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户互粉
 *
 * @author: yunfei
 * @create: 2019-08-01 11:26
 **/

@Service
public class SkFansServiceImpl implements SkFansService {
    private Logger logger = LoggerFactory.getLogger(SkFansServiceImpl.class);

    @Autowired
    private SkFansDAO skFansDAO;

    @Autowired
    private RabbitmqSender rabbitmqSender;
    @Autowired
    private RedisService redisService;


    @Override
    public BaseResponse attentionOrNot(AttentionRequest params) {
        if (params.getUserId() <= 0) {
            return BaseResponse.newFailure(ResponseCode.REQUIRED_PARAMS_MISSING);
        }
        SkFans fans = skFansDAO.checkFansState(params.getAttentionUserId(), params.getUserId());
        if (params.getAttentionType() == 0) {
            SkFans skFans = attentionUser(fans, params);
            if (skFans != null) {
                sendNoticMsg(skFans);
            }
        } else {
            notAttentionUser(fans, params);
        }
        return BaseResponse.newSuccess();
    }


    @Override
    public List<SkFansVO> attentionFansList(FansRequest params) {
        try {
            return skFansDAO.attentionFansList(params);
        } catch (Exception e) {
            logger.error("attentionFansList is error！！！params={} e={}", params, e);
        }
        return new ArrayList<>(0);
    }

    @Override
    public List<SkFansVO> findFansList(FansRequest params) {
        try {
            return skFansDAO.findFansList(params);
        } catch (Exception e) {
            logger.error("findFansList is error！！！params={} e={}", params, e);
        }
        return new ArrayList<>(0);
    }

    @Override
    public List<SkFansVO> otterAttentionFansList(FansRequest params, Long loginUserId) {
        //关注或粉丝
        List<SkFansVO> skFansVOS = skFansDAO.attentionFansList(params);
        if (null != loginUserId && loginUserId > 0 && !CollectionUtils.isEmpty(skFansVOS)) {
            Set<String> attentionUserIds = skFansVOS.stream().map(s -> {
                return String.valueOf(s.getUserId());
            }).collect(Collectors.toSet());
            //当前登陆用户自己关注的用户
            List<String> myAttentionUsers = skFansDAO.selectMyFans(loginUserId, attentionUserIds);
            if (!CollectionUtils.isEmpty(myAttentionUsers)) {
                skFansVOS.stream().filter(vo -> myAttentionUsers.contains(String.valueOf(vo.getUserId()))).map(f -> {
                    f.setFansState(UserConstant.subscrip_status_1);
                    return f;
                }).collect(Collectors.toList());
            }
        }
        return skFansVOS;
    }

    /**
     * 取消关注用户
     *
     * @param fans
     * @param params
     */
    public void notAttentionUser(SkFans fans, AttentionRequest params) {
        int count = skFansDAO.deleteAttentionUser(params.getUserId(), params.getAttentionUserId());
        if (count > 0) {
            // 维护被关注用户关注维度-1
            sendUserBaseDataMsg(params.getAttentionUserId(), -1, UserBaseTypeEnum.FANS_NUM.getCode());
            sendUserBaseDataMsg(params.getUserId(), -1, UserBaseTypeEnum.ATTENTION_NUM.getCode());
            if (fans != null) {
                fans.setFansState((byte) 0);
                skFansDAO.updateByPrimaryKeySelective(fans);
            }
        }
    }

    /**
     * 关注用户
     *
     * @param fans
     * @param params
     */
    public SkFans attentionUser(SkFans fans, AttentionRequest params) {
        SkFans skFans = skFansDAO.checkFansState(params.getUserId(), params.getAttentionUserId());
        if (skFans == null) {
            skFans = new SkFans();
            skFans.setUserId(params.getUserId());
            skFans.setAttentionUserId(params.getAttentionUserId());
            //以被关注用户角度查询当前用户是否被关注
            skFans.setFansState((byte) (fans == null ? 0 : 1));
            skFans.setCreateTime(new Date());
            int count = skFansDAO.insertSelective(skFans);
            if (count > 0) {
                //  维护被关注用户关注维度+1
                sendUserBaseDataMsg(skFans.getAttentionUserId(), 1, UserBaseTypeEnum.FANS_NUM.getCode());
                //  维护我关注的用户数+1
                sendUserBaseDataMsg(skFans.getUserId(), 1, UserBaseTypeEnum.ATTENTION_NUM.getCode());
                if (fans != null) {
                    fans.setFansState((byte) 1);
                    skFansDAO.updateByPrimaryKeySelective(fans);
                }
            }
            return skFans;
        }
        return null;
    }

    /***
     * 更新用户冗余基础数据
     * @param userId
     * @param num
     * @param code
     */
    private final void sendUserBaseDataMsg(Long userId, Integer num, int code) {
        UserBaseDataMsg msg = new UserBaseDataMsg();
        msg.setNum(num);
        msg.setUserId(userId);
        msg.setCode(code);
        rabbitmqSender.sendUserDataMessage(msg);
    }

    private final void sendNoticMsg(SkFans fans) {
        NoticeEvent msg = new NoticeEvent();
        msg.setType(NoticeEvent.type_attenttion);
        msg.setEventId(fans.getId());
        msg.setTime(System.currentTimeMillis());
        rabbitmqSender.sendFansNoticeMsg(msg);
        TaskEvent taskEvent = new TaskEvent();
        taskEvent.setType(3);
        taskEvent.setUserId(fans.getUserId());
        rabbitmqSender.sendTaskMsg(taskEvent, "task.guanzhu");
    }

    public SkFans findById(Long id) {
        return skFansDAO.selectByPrimaryKey(id);
    }
}
