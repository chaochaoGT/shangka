package com.geek.shengka.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.geek.shengka.common.exception.BaseException;
import com.geek.shengka.user.entity.SkUserBaseInfo;
import com.geek.shengka.user.entity.vo.SkUserBaseInfoUpdateVO;
import com.geek.shengka.user.entity.vo.SkUserBaseInfoVO;
import com.geek.shengka.user.entity.vo.SkUserCenterExtendVO;
import com.geek.shengka.user.mapper.SkUserBaseInfoDAO;
import com.geek.shengka.user.service.SkUserInfoService;
import com.geek.shengka.user.utils.BeanMapperUtils;
import com.geek.shengka.user.utils.CommonUtil;
import com.geek.shengka.user.utils.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.geek.shengka.user.constant.CommonConstant.*;

/**
 * @author qubianzhong
 * @date 2019/8/7 11:25
 */
@Service
@Slf4j
public class SkUserInfoServiceImpl implements SkUserInfoService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisService redisService;

    @Value("${user.center.remote.url}")
    private String userCenterUrl;
    @Value("${user.center.header.platform-id}")
    private String userCenterPlatformId;
    @Value("${user.center.header.source}")
    private String userCenterSource;

    @Autowired
    private SkUserBaseInfoDAO skUserBaseInfoDAO;

    /**
     * 通过UserId来查询个人中心详情
     *
     * @param userId
     * @return com.geek.shengka.user.entity.vo.SkUserBaseInfoVO
     * @author qubianzhong
     * @Date 11:34 2019/8/7
     */
    @Override
    public SkUserBaseInfoVO center(Long userId) {
        return getInfoNoCache(null, userId);
    }

    /**
     * 通过UserId来查询个人主页详情
     *
     * @param userId
     * @return com.geek.shengka.user.entity.vo.SkUserBaseInfoVO
     * @author qubianzhong
     * @Date 13:49 2019/8/7
     */
    @Override
    public SkUserBaseInfoVO baseInfo(Long userId) {
        Assert.notNull(userId, "用户id不能为空");
        return skUserBaseInfoDAO.info(null, userId);
    }

    /**
     * 黑名单用户IDS
     *
     * @param userId
     * @return java.util.List<java.lang.Long>
     * @author qubianzhong
     * @Date 17:12 2019/8/15
     */
    @Override
    public List<Long> blackListIds(Long userId) {
        return skUserBaseInfoDAO.blackListIds(userId);
    }

    @Override
    public SkUserBaseInfoVO info(Long userId, Long attentionUserId) {
        return getInfoByCache(userId, attentionUserId);
    }

    /**
     * @param infoAddVO
     * @param request
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 10:43 2019/8/8
     */
    @Override
    public Boolean update(SkUserBaseInfoUpdateVO infoAddVO, HttpServletRequest request) {
        log.info("update info:{}", JSONObject.toJSONString(infoAddVO));
        SkUserBaseInfo userBaseInfo = BeanMapperUtils.map(infoAddVO, SkUserBaseInfo.class);
        boolean updated = skUserBaseInfoDAO.updateByUserIdSelective(userBaseInfo) > 0;
        if (!updated) {
            throw new BaseException("用户信息不存在");
        }
        if (!StringUtils.isEmpty(userBaseInfo.getSkCode())) {
            String skCode = redisService.getString("sk:code:" + userBaseInfo.getSkCode());
            if (skCode == null) {
                redisService.pushWithTime("sk:code:" + userBaseInfo.getSkCode(), userBaseInfo.getSkCode(), 30 * 24 * 60 * 60L, TimeUnit.SECONDS);
            }
        }
        SkUserCenterExtendVO extendVO = infoAddVO.getExtendVO();
        if (extendVO != null) {
            updateExtendInfo(extendVO, request);
        }
        return true;
    }

    /**
     * 通过声咖号查询USerId
     *
     * @param skCode
     * @return java.lang.Long
     * @author qubianzhong
     * @Date 13:43 2019/8/8
     */
    @Override
    public Long selectUserIdBySkCode(String skCode) {
        return skUserBaseInfoDAO.selectUserIdBySkCode(skCode);
    }

    private void updateExtendInfo(SkUserCenterExtendVO extendVO, HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add("platform-id", userCenterPlatformId);
        headers.add("source", userCenterSource);
        headers.add("app-id", request.getHeader("app-id"));
        headers.add("token", request.getHeader("token"));
        headers.add("sign", request.getHeader("sign"));
        String body = JSONObject.toJSONString(extendVO);
        log.info("用户中心更新个人扩展消息：body={}", body);
        HttpEntity httpEntity = new HttpEntity(body, headers);
        JSONObject result = restTemplate.postForEntity(userCenterUrl + "/v1/setUserInfo", httpEntity, JSONObject.class).getBody();
        String code = result.getString("code");
        if ("".equals(code.replace("0", ""))) {
            return;
        }
        String message = result.getString("message");
        log.error("调用用户中心修改用户详情接口异常：code={},message={}", code, message);
        throw new BaseException(message);
    }

    private SkUserBaseInfoVO getInfo(Long userId, Long attentionUserId, boolean useCache) {
        SkUserBaseInfoVO infoVO = skUserBaseInfoDAO.info(userId, attentionUserId);
        SkUserCenterExtendVO extendVO = null;
        String redisKey = REDIS_KEY_USER_ATTENTION + REDIS_KEY_SPLIT + attentionUserId;
        //数据库不存在此用户
        if (infoVO == null) {
            JSONObject result = getInfoById(attentionUserId);
            if (result == null || result.size() == 0) {
                throw new BaseException("当前用户不存在");
            }
            extendVO = BeanMapperUtils.map(result.getJSONObject("userExtend"), SkUserCenterExtendVO.class);
            JSONObject info = result.getJSONObject("userInfoResponse");
            if (extendVO != null && info != null) {
                extendVO.setPhoneNum(info.getString("phoneNum"));
                extendVO.setWeChat(info.getString("wechat"));
            }
            String skCode = String.valueOf(Instant.now().toEpochMilli()).substring(4) + CommonUtil.getRandomNumber(5);
            //插入数据库
            insertBaseInfo(info, attentionUserId, skCode);
            //初始化infoVO
            infoVO = initInfoVO(info, attentionUserId, skCode);
            infoVO.setExtendVO(extendVO);
        } else {
            if (useCache) {
                //如果缓存中存在，则从缓存中读取数据
                String infoStr = redisService.getString(redisKey);
                if (!StringUtils.isEmpty(infoStr)) {
                    extendVO = JSONObject.parseObject(infoStr, SkUserCenterExtendVO.class);
                }
            }
            if (extendVO == null) {
                //用户中心
                JSONObject result = getInfoById(attentionUserId);
                if (result == null || result.size() == 0) {
                    throw new BaseException("当前用户不存在");
                }
                extendVO = BeanMapperUtils.map(result.getJSONObject("userExtend"), SkUserCenterExtendVO.class);
                JSONObject info = result.getJSONObject("userInfoResponse");
                if (extendVO != null && info != null) {
                    extendVO.setPhoneNum(info.getString("phoneNum"));
                    extendVO.setWeChat(info.getString("wechat"));
                    //更新用户信息缓存，缓存时间30分钟
                    redisService.pushWithTime(redisKey, JSONObject.toJSONString(extendVO), REDIS_KEY_USER_ATTENTION_TIME, TimeUnit.SECONDS);
                }
            }
            infoVO.setExtendVO(extendVO);
        }
        return infoVO;
    }

    private void insertBaseInfo(JSONObject info, Long attentionUserId, String skCode) {
        try {
            //新增初始化
            SkUserBaseInfo baseInfo = new SkUserBaseInfo();
            baseInfo.setUserId(attentionUserId);
            baseInfo.setSkCode(skCode);
            baseInfo.setNickName(info.getString("nickname"));
            baseInfo.setUserIcon(info.getString("userAvatar"));
            skUserBaseInfoDAO.insertSelective(baseInfo);
        } catch (Exception e) {
            log.error("新增初始化用户异常：" + e.getMessage());
        }
    }

    private SkUserBaseInfoVO initInfoVO(JSONObject info, Long attentionUserId, String skCode) {
        SkUserBaseInfoVO infoVO = new SkUserBaseInfoVO();
        infoVO.setUserId(attentionUserId);
        infoVO.setNickName(info.getString("nickname"));
        infoVO.setUserIcon(info.getString("userAvatar"));
        infoVO.setVoiceNum(0);
        infoVO.setAttention(0);
        infoVO.setAttentionNum(0);
        infoVO.setFansNum(0);
        infoVO.setGender(2);
        infoVO.setLikedNum(0);
        infoVO.setLikeWorksNum(0);
        infoVO.setNearestNum(0);
        infoVO.setPublishNum(0);
        infoVO.setSubscribeTopicNum(0);
        infoVO.setThumbsNum(0);
        infoVO.setSkCode(skCode);
        return infoVO;
    }

    private SkUserBaseInfoVO getInfoByCache(Long userId, Long attentionUserId) {
        return getInfo(userId, attentionUserId, true);
    }

    private SkUserBaseInfoVO getInfoNoCache(Long userId, Long attentionUserId) {
        return getInfo(userId, attentionUserId, false);
    }

    private JSONObject getInfoById(Long userId) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add("platform-id", userCenterPlatformId);
        headers.add("source", userCenterSource);
        HttpEntity httpEntity = new HttpEntity(headers);
        JSONObject result = restTemplate.exchange(userCenterUrl + "/v2/showUser?userId=" + userId, HttpMethod.GET, httpEntity, JSONObject.class).getBody();
        String code = result.getString("code");
        if ("".equals(code.replace("0", ""))) {
            return result.getJSONObject("data");
        }
        String message = result.getString("message");
        log.error("调用用户中心用户详情接口异常：userId={},code={},message={}", userId, code, message);
        throw new BaseException(message);
    }
}
