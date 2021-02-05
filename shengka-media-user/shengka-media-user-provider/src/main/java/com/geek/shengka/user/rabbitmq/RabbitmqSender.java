package com.geek.shengka.user.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.geek.shengka.common.mq.NoticeEvent;
import com.geek.shengka.common.mq.UserBaseDataMsg;
import com.geek.shengka.user.rabbitmq.entity.TaskEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * @author yunfei
 */
@Service
public class RabbitmqSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public final static Logger logger = LoggerFactory.getLogger(RabbitmqSender.class);

    /**
     * 维护用户冗余基础数据数据，发送消息
     *
     * @param message
     */
    public void sendUserDataMessage(UserBaseDataMsg message) {
        try {
            String sendMsg = JSON.toJSONString(message);
            rabbitTemplate.convertAndSend("userBaseDataXchange", "userBaseData.sync", sendMsg);
            logger.info("发送同步信息到UserBaseInfo表消息：" + sendMsg);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 消息通知事件
     *
     * @param message
     */
    public void sendFansNoticeMsg(NoticeEvent message) {
        try {
            String sendMsg = JSON.toJSONString(message);
            rabbitTemplate.convertAndSend("noticeexchange", "notice.fans", sendMsg);
            logger.info("发送关注好友通知消息：" + sendMsg);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 任务通知事件
     *
     * @param message
     */
    public void sendTaskMsg(TaskEvent message,String key) {
        try {
            String sendMsg = JSON.toJSONString(message);
            rabbitTemplate.convertAndSend("taskexchange",key, sendMsg);
            logger.info("发送任务消息：" + sendMsg);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}  