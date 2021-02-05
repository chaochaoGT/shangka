package com.geek.shengka.user.service.impl;

import com.geek.shengka.common.enums.UserBaseTypeEnum;
import com.geek.shengka.common.mq.UserBaseDataMsg;
import com.geek.shengka.user.constant.UserConstant;
import com.geek.shengka.user.controller.SkTopicController;
import com.geek.shengka.user.entity.SkSubscribeTopic;
import com.geek.shengka.user.entity.vo.SkTopicConfigVO;
import com.geek.shengka.user.mapper.SkSubscribeTopicDAO;
import com.geek.shengka.user.rabbitmq.RabbitmqSender;
import com.geek.shengka.user.service.SkTopicService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Filename: SkTopicServiceImpl
 * @Description:
 * @Version: 1.0
 * @Author: wangchao
 * @Email: wangchao@hellogeek.com
 * @date: 2019/8/8 ;
 */
@Service
public class SkTopicServiceImpl implements SkTopicService {
    private Logger logger= LoggerFactory.getLogger(SkTopicController.class);
    @Autowired
    SkSubscribeTopicDAO skSubscribeTopicDAO;
    @Autowired
    RabbitmqSender rabbitmqSender;

    @Override
    public void subscripTopic(Long userId, Long topicId, Integer status) {
        SkSubscribeTopic st=skSubscribeTopicDAO.selectByTopicIdUid(topicId,userId);
        if (UserConstant.subscrip_status_0==status && Objects.isNull(st)){
            //add
            st=new SkSubscribeTopic();
            st.setCreateTime(new Date());
            st.setTopicId(topicId);
            st.setUserId(userId);
            skSubscribeTopicDAO.insertSelective(st);
            sendUserDataMessage(userId,1,UserBaseTypeEnum.SUBSCRIBE_TOPIC_NUM.getCode());
        }else if (UserConstant.subscrip_status_0 == status && !Objects.isNull(st)){
            logger.warn("Topic already subscribed !!!");
        } else if (UserConstant.subscrip_status_1==status &&  !Objects.isNull(st)){
            skSubscribeTopicDAO.deleteByTopicUid(topicId,userId);
            sendUserDataMessage(userId,-1,UserBaseTypeEnum.SUBSCRIBE_TOPIC_NUM.getCode());
        } else if (UserConstant.subscrip_status_1==status &&  Objects.isNull(st)){
            logger.warn("subscriTopic is empty!!!");
        }
    }

    @Override
    public List<SkTopicConfigVO> attentionTopicList(Long userId, int pageIndex, int pageSize) {
        List<SkTopicConfigVO> skTopicConfigVOS = new ArrayList<>();
        if (userId<=0){
            return skTopicConfigVOS;
        }
        skTopicConfigVOS = skSubscribeTopicDAO.attentionTopicList(userId,
                (pageIndex-1)*pageSize, pageSize);
        return  skTopicConfigVOS;
    }

    /**
     * 发送订阅消息
     * @param userId
     * @param i
     * @param code
     */
    private void sendUserDataMessage(Long userId, int i, int code) {
        UserBaseDataMsg msg=new UserBaseDataMsg();
        msg.setNum(i);
        msg.setUserId(userId);
        msg.setCode(code);
        rabbitmqSender.sendUserDataMessage(msg);
    }


}
