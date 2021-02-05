package com.geek.shengka.gold.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.geek.shengka.gold.request.MqCoinMsg;

@Service  
public class RabbitmqSender {  

	public final static Logger logger = LoggerFactory.getLogger(RabbitmqSender.class);
	
	@Autowired
	private RabbitTemplate rabbitTemplate;  
	
    // 发送消息
    public void sendCoinMessage(MqCoinMsg message) {
        try{
        	String  sendMsg=JSON.toJSONString(message);
        	
    		rabbitTemplate.convertAndSend("coinexchange", "coin.syncgold", sendMsg);

        }catch (Exception e){  
        	logger.error(e.getMessage(),e);
        }  
    }

}  