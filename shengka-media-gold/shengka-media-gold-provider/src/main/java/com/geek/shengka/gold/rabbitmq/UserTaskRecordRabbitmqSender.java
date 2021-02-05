package com.geek.shengka.gold.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.geek.shengka.gold.request.MqCoinMsg;
import com.geek.shengka.gold.request.UserTaskRecordMqRequest;
import com.geek.shengka.gold.request.UserTaskRecordNoticeMqRequest;

/**
 * 每日任务领取金币
 * @author xuxuelei
 *
 */
@Service  
public class UserTaskRecordRabbitmqSender {  

	public final static Logger logger = LoggerFactory.getLogger(UserTaskRecordRabbitmqSender.class);
	
	@Autowired
	private RabbitTemplate rabbitTemplate;  
	
    // 发送消息
    public void sendUserRecordMessage(UserTaskRecordMqRequest message) {
        try{
        	String  sendMsg=JSON.toJSONString(message);
        	
    		rabbitTemplate.convertAndSend("coinAccountexchange", "coinaccount.usertaskrecord", sendMsg);
        }catch (Exception e){  
        	logger.error(e.getMessage(),e);
        }  
    }
    
    
	
    // 发送Task消息
    public void sendTaskUserRecordMessage(UserTaskRecordNoticeMqRequest message) {
        try{
        	String  sendMsg=JSON.toJSONString(message);
        	
    		rabbitTemplate.convertAndSend("taskcoinexchange", "taskcoin.usertaskrecord", sendMsg);
        }catch (Exception e){  
        	logger.error(e.getMessage(),e);
        }  
    }

}  