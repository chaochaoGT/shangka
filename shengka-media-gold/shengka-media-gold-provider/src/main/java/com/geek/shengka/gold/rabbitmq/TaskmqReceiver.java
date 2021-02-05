package com.geek.shengka.gold.rabbitmq;

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
import com.geek.shengka.gold.rabbitmq.entity.GetTaskCoinEvent;
import com.geek.shengka.gold.rabbitmq.entity.TaskEvent;
import com.geek.shengka.gold.service.impl.TaskServiceImpl;
import com.rabbitmq.client.Channel;

@Service
public class TaskmqReceiver{

    public final static Logger logger = LoggerFactory.getLogger(TaskmqReceiver.class);
    
    @Autowired
    private  TaskServiceImpl taskServiceImpl;

	@RabbitListener(bindings = @QueueBinding(
			value = @Queue(value ="taskqueue", 
			durable="true"),
			exchange = @Exchange(value = "taskexchange", 
			durable="true", 
			type= "topic", 
			ignoreDeclarationExceptions = "true"),
			key = "task.*"
			)
	)
	@RabbitHandler
    public void receiveMsg(Message<String> message, Channel channel) {
	
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        try {
            channel.basicAck(deliveryTag, false);
            TaskEvent taskEvent = JSON.parseObject(message.getPayload(),
                    new TypeReference<TaskEvent>() {
                    });
            logger.info("监听到触发任务事件:" + JSON.toJSONString(taskEvent));
            
            taskServiceImpl.taskEventProcess(taskEvent);
            
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
    }


}
