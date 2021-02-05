package com.geek.shengka.user.rabbitmq;

import com.geek.shengka.common.mq.NoticeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.geek.shengka.user.rabbitmq.entity.CommentAndAtNoticeEvent;
import com.geek.shengka.user.service.impl.NoticeServiceImpl;
import com.rabbitmq.client.Channel;

@Service
public class NoticemqReceiver{

    public final static Logger logger = LoggerFactory.getLogger(NoticemqReceiver.class);
    
    @Autowired
    private  NoticeServiceImpl noticeServiceImpl;

	@RabbitListener(bindings = @QueueBinding(
			value = @Queue(value ="noticequeue", 
			durable="true"),
			exchange = @Exchange(value = "noticeexchange", 
			durable="true", 
			type= "topic", 
			ignoreDeclarationExceptions = "true"),
			key = "notice.*"
			)
	)
	@RabbitHandler
    public void receiveMsg(Message<String> message, Channel channel) {
		
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        try {
            channel.basicAck(deliveryTag, false);
            NoticeEvent noticeEvent = JSON.parseObject(message.getPayload(),
                    new TypeReference<NoticeEvent>() {
                    });
            logger.info("监听到触发点赞或关注通知事件:" + JSON.toJSONString(noticeEvent));
            
            noticeServiceImpl.noticeProcess(noticeEvent);
            
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
    }


	@RabbitListener(bindings = @QueueBinding(
			value = @Queue(value ="commentnoticequeue", 
			durable="true"),
			exchange = @Exchange(value = "commentnoticeexchange", 
			durable="true", 
			type= "topic", 
			ignoreDeclarationExceptions = "true"),
			key = "commentnotice"
			)
	)
	@RabbitHandler
    public void receiveCommentNoticeMsg(Message<String> message, Channel channel) {
		
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        try {
            channel.basicAck(deliveryTag, false);
            CommentAndAtNoticeEvent commentAndAtEvent = JSON.parseObject(message.getPayload(),
                    new TypeReference<CommentAndAtNoticeEvent>() {
                    });
            logger.info("监听到触发评论或at通知事件:" + JSON.toJSONString(commentAndAtEvent));
            
            noticeServiceImpl.commentNoticeProcess(commentAndAtEvent);
            
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
    }

}
