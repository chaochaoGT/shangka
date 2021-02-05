package com.geek.shengka.content.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.geek.shengka.common.mq.NoticeEvent;
import com.geek.shengka.common.mq.UserBaseDataMsg;
import com.geek.shengka.common.request.ContextReportRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
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
            logger.info("send user base data：" + sendMsg);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /***
     * 上报内容中心
     * @param message
     */
    public void sendContentReportMessage(ContextReportRequest message) {
        try {
            String sendMsg = JSON.toJSONString(message);
            rabbitTemplate.convertAndSend("contentReportXchange", "contentReport.msg", sendMsg);
            logger.info("send content report data：" + sendMsg);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }


    /**
     * 消息通知事件
     *
     * @param message
     */
    public void sendCommentNoticeMsg(NoticeEvent message) {
        try {
            String sendMsg = JSON.toJSONString(message);
            rabbitTemplate.convertAndSend("noticeexchange", "notice.msg", sendMsg);
            logger.info("send notic  data：" + sendMsg);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}  