package com.geek.shengka.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.geek.shengka.common.annotation.OnlyUserIgnoreToken;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.user.entity.vo.SkUserBaseInfoUpdateVO;
import com.geek.shengka.user.entity.vo.SkUserBaseInfoVO;
import com.geek.shengka.user.enums.UserInfoGenderEnum;
import com.geek.shengka.user.rabbitmq.RabbitmqSender;
import com.geek.shengka.user.rabbitmq.entity.TaskEvent;
import com.geek.shengka.user.service.SkUserInfoService;
import com.geek.shengka.user.utils.CommonUtil;
import com.geek.shengka.user.utils.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.geek.shengka.user.constant.CommonConstant.REDIS_KEY_SPLIT;
import static com.geek.shengka.user.constant.CommonConstant.REDIS_KEY_USER_ATTENTION;

/**
 * 用户信息
 *
 * @author qubianzhong
 * @Date 11:24 2019/8/7
 */
@RestController
@RequestMapping("/v1/userInfo")
@Slf4j
public class SkUserInfoController extends BaseController {

    @Autowired
    private SkUserInfoService skUserInfoService;
    @Autowired
    private RedisService redisService;

    @Autowired
    private RabbitmqSender rabbitmqSender;

    /**
     * 个人中心页
     *
     * @return com.geek.shengka.common.basemodel.BaseResponse
     * @author qubianzhong
     * @Date 11:25 2019/8/7
     */
    @GetMapping("/center")
    public BaseResponse<SkUserBaseInfoVO> center() {
        Long userId = getAndCheckUserId();

        SkUserBaseInfoVO skUserBaseInfoVO = skUserInfoService.center(userId);
        log.info("center接口：userId={},data={}", userId, JSONObject.toJSONString(skUserBaseInfoVO));
        if (skUserBaseInfoVO != null) {
            TaskEvent taskEvent = new TaskEvent();
            taskEvent.setType(4);
            taskEvent.setUserId(skUserBaseInfoVO.getUserId());
            /**
             * 发送用户登录任务消息
             */
            rabbitmqSender.sendTaskMsg(taskEvent, "task.login");
        }
        return BaseResponse.newSuccess(skUserBaseInfoVO);
    }


    private Long getAndCheckUserId() {
        Long userId = getUserId();
        checkUserId(userId);
        return userId;
    }

    /**
     * 个人主页--别人访问
     *
     * @param attentionUserId
     * @return com.geek.shengka.common.basemodel.BaseResponse<com.geek.shengka.user.entity.vo.SkUserBaseInfoVO>
     * @author qubianzhong
     * @Date 13:52 2019/8/7
     */
    @GetMapping("/info")
    @OnlyUserIgnoreToken
    public BaseResponse<SkUserBaseInfoVO> info(@RequestParam(value = "userId", required = false) Long attentionUserId) {
        Long userId = getUserId();
        if (attentionUserId == null) {
            checkUserId(userId);
            log.info("{}查看自己的个人主页", userId);
            return BaseResponse.newSuccess(skUserInfoService.center(userId));
        }

        log.info("{}查看{}的个人主页", userId, attentionUserId);
        SkUserBaseInfoVO infoVO = skUserInfoService.info(userId, attentionUserId);

        return BaseResponse.newSuccess(infoVO);
    }

    /**
     * 更新个人信息
     *
     * @param request
     * @param infoVO
     * @return com.geek.shengka.common.basemodel.BaseResponse<java.lang.Boolean>
     * @author qubianzhong
     * @Date 10:43 2019/8/8
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> update(HttpServletRequest request, @RequestBody SkUserBaseInfoUpdateVO infoVO) {
        log.info("用户信息更新：" + JSONObject.toJSONString(infoVO));
        infoVO = checkInfoVO(infoVO);
        if (!StringUtils.isEmpty(infoVO.getSkCode()) && infoVO.getSkCode().length() > 16) {
            return BaseResponse.newFailure(-1, "声咖号不能超过16个字符");
        }
        if (!StringUtils.isEmpty(infoVO.getSkCode()) && !CommonUtil.checkSkCode(infoVO.getSkCode())) {
            return BaseResponse.newFailure(-1, "声咖号只允许包含数字、字母、下划线、点(英文)");
        }
        if (!StringUtils.isEmpty(infoVO.getSkCode())) {
            SkUserBaseInfoVO infoVO1 = skUserInfoService.info(null, infoVO.getUserId());
            String skCode = redisService.getString("sk:code:" + infoVO1.getSkCode());
            if (skCode != null && !skCode.equals(infoVO.getSkCode())) {
                return BaseResponse.newFailure(-1, "声咖号30天只能修改一次！");
            }
            Long userId = skUserInfoService.selectUserIdBySkCode(infoVO.getSkCode());
            if (userId != null && !userId.equals(getUserId())) {
                return BaseResponse.newFailure(-1, "声咖号已经被占用！");
            }
        }
        if (infoVO.getGender() != null
                && !UserInfoGenderEnum.isExist(infoVO.getGender())) {
            return BaseResponse.newFailure(-1, "性别参数异常！");
        }
        if (infoVO.getUserId() == null) {
            infoVO.setUserId(getUserId());
        }
        String redisKey = REDIS_KEY_USER_ATTENTION + REDIS_KEY_SPLIT + infoVO.getUserId();
        redisService.deleteKey(redisKey);

        return BaseResponse.newSuccess(skUserInfoService.update(infoVO, request));
    }

    private SkUserBaseInfoUpdateVO checkInfoVO(SkUserBaseInfoUpdateVO infoVO) {
        if (infoVO == null) {
            infoVO = new SkUserBaseInfoUpdateVO();
        }
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(infoVO));
        for (String key : jsonObject.keySet()) {
            if (StringUtils.isEmpty(jsonObject.getString(key))) {
                jsonObject.put(key, null);
            }
        }
        infoVO = JSONObject.parseObject(jsonObject.toJSONString(), SkUserBaseInfoUpdateVO.class);
        if (infoVO.getUserId() == null) {
            infoVO.setUserId(getAndCheckUserId());
        }
        return infoVO;
    }


    /**
     * 该用户的黑名单--不分页
     *
     * @return com.geek.shengka.common.basemodel.BaseResponse<java.util.List < java.lang.Long>>
     * @author qubianzhong
     * @Date 17:17 2019/8/15
     */
    @GetMapping("/blackListIds")
    public BaseResponse<List<Long>> blackListIds() {
        return BaseResponse.newSuccess(skUserInfoService.blackListIds(getUserId()));
    }
}
