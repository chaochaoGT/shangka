package com.geek.shengka.content.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.geek.shengka.common.mq.NoticeEvent;
import com.geek.shengka.common.mq.UserBaseDataMsg;
import com.geek.shengka.content.request.ContentFriendMqRequest;
import com.geek.shengka.content.request.ThumbsUpMqRequest;
import com.geek.shengka.content.request.VideoMqRequest;
import com.geek.shengka.content.request.VoiceMqRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xuxuelei
 */
@Service
public class ContentRabbitmqSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public final static Logger logger = LoggerFactory.getLogger(ContentRabbitmqSender.class);

    /**
     * 发布视频 上报mq
     *
     * @param message
     */
    public void sendVideoMessage(VideoMqRequest message) {
        try {
            String sendMsg = JSON.toJSONString(message);
            rabbitTemplate.convertAndSend("taskexchange", "task.video", sendMsg);
            logger.info("发送上传视频任务消息：" + sendMsg);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /***
     * 发布语音上报mq
     * @param message
     */
    public void sendVoiceMessage(VoiceMqRequest message) {
        try {
            String sendMsg = JSON.toJSONString(message);
            rabbitTemplate.convertAndSend("taskexchange", "task.voice", sendMsg);
            logger.info("发送上传语音任务消息：" + sendMsg);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    
    /***
     * 点赞上报mq
     * @param message
     */
    public void sendThumbsUpMessage(NoticeEvent message) {
        try {
            String sendMsg = JSON.toJSONString(message);
            rabbitTemplate.convertAndSend("noticeexchange", "notice.thumbsup", sendMsg);
            logger.info("发送点赞通知消息：" + sendMsg);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
    
    
    
    /***
     *  用户发语音评论通知,用户发视频@被通知人通知,用户语音@被通知人通知,
     * @param message
     */
    public void sendFriendMessage(ContentFriendMqRequest message) {
        try {
            String sendMsg = JSON.toJSONString(message);
            rabbitTemplate.convertAndSend("commentnoticeexchange", "commentnotice", sendMsg);
            logger.info("发送视频评论或@好友通知消息：" + sendMsg);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
    
    
    /***
     *  消费者， 维护用户冗余基础数据
     * @param message
     */
    public void sendUserActionMessage(UserBaseDataMsg message) {
        try {
            String sendMsg = JSON.toJSONString(message);
            rabbitTemplate.convertAndSend("userBaseDataXchange", "userBaseData.sync", sendMsg);
            logger.info("发送同步信息到UserBaseInfo表消息：" + sendMsg);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
    
    
}  