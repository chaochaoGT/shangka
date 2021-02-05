package com.geek.shengka.gold.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.gold.entity.SkUserTaskRecord;
import com.geek.shengka.gold.entity.SkUserTreasureBoxRecord;
import com.geek.shengka.gold.mapper.SkUserTaskRecordDAO;
import com.geek.shengka.gold.mapper.SkUserTreasureBoxRecordDAO;
import com.geek.shengka.gold.proxy.AccountingCenterProxy;
import com.geek.shengka.gold.proxy.RemoteIoRecorder;
import com.geek.shengka.gold.rabbitmq.entity.GetTaskCoinEvent;
import com.geek.shengka.gold.request.MqCoinMsg;
import com.geek.shengka.gold.request.SyncCoinRequest;
import com.geek.shengka.gold.request.UserTaskRecordNoticeMqRequest;
import com.geek.shengka.gold.service.impl.TaskServiceImpl;
import com.geek.shengka.gold.util.ClearRediskeyUtil;
import com.rabbitmq.client.Channel;
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

/**
 * @author yunfei
 */
@Service
public class RabbitmqReceiver extends RemoteIoRecorder {

    public final static Logger logger = LoggerFactory.getLogger(RabbitmqReceiver.class);


    @Autowired
    private AccountingCenterProxy accountingCenterProxy;

    @Autowired
    private SkUserTreasureBoxRecordDAO skUserTreasureBoxRecordDAO;
    @Autowired
    private SkUserTaskRecordDAO skUserTaskRecordDAO;

    @Autowired
    private UserTaskRecordRabbitmqSender userTaskRecordRabbitmqSender;
    
    @Autowired
    private  TaskServiceImpl taskServiceImpl;

	@RabbitListener(bindings = @QueueBinding(
			value = @Queue(value ="coinqueue", 
			durable="true"),
			exchange = @Exchange(value = "coinexchange", 
			durable="true", 
			type= "topic", 
			ignoreDeclarationExceptions = "true"),
			key = "coin.*"
			)
	)
	@RabbitHandler
    public void receiverUserActionMsg(Message<String> message, Channel channel) {
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        try {
            channel.basicAck(deliveryTag, false);
            MqCoinMsg coinMsg = JSON.parseObject(message.getPayload(),
                    new TypeReference<MqCoinMsg>() {
                    });
            if(coinMsg.getUserId()==null){
                logger.warn("rabbitmq参数为NUll:" + JSON.toJSONString(coinMsg));
                return;
            }
            logger.info("rabbitmq监听处理完对象:" + JSON.toJSONString(coinMsg));
            SkUserTreasureBoxRecord record = skUserTreasureBoxRecordDAO.selectByOrderNo(coinMsg.getOrderNo());
            if(record==null){
                return;
            }
            SyncCoinRequest coinRequest = new SyncCoinRequest();
            coinRequest.setUserId(coinMsg.getUserId());
            coinRequest.setGoldCoin(coinMsg.getGoldCoin());
            coinRequest.setOrderNo(coinMsg.getOrderNo());
            coinRequest.setTradeTypeCode(coinMsg.getTradeTypeCode());
            coinRequest.setExt(coinMsg.getExt());
            BaseResponse response = accountingCenterProxy.syncGoldCoinFlow(coinRequest);
            //账户可能发生变动，清除账户缓存
            ClearRediskeyUtil.clearCachekey(coinMsg.getUserId());
            String tradeCode2 = null;
            int code2 = response.getCode();
            if (code2==0) {
                if (null != response.getData()) {
                    tradeCode2 = response.getData().toString();
                }
                record.setTradeNo(tradeCode2);
                record.setHandleState((byte) 1);
            } else {
                //所有失败都记录，后续手动处理
                record.setHandleState((byte) 2);
            }
            record.setHandleRemark(JSON.toJSONString(response));
            record.setProcessCts(record.getProcessCts()+1);
            skUserTreasureBoxRecordDAO.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
    }

	
	@RabbitListener(bindings = @QueueBinding(
			value = @Queue(value ="coinAccountqueue", 
			durable="true"),
			exchange = @Exchange(value = "coinAccountexchange", 
			durable="true", 
			type= "topic", 
			ignoreDeclarationExceptions = "true"),
			key = "coinaccount.*"
			)
	)
	@RabbitHandler
    public void receiverAccountUserActionMsg(Message<String> message, Channel channel) {
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        try {
            channel.basicAck(deliveryTag, false);
            
            MqCoinMsg coinMsg = JSON.parseObject(message.getPayload(),
                    new TypeReference<MqCoinMsg>() {
                    });
            logger.info("rabbitmq监听处理完对象:" + JSON.toJSONString(coinMsg));
            SyncCoinRequest coinRequest = new SyncCoinRequest();
            coinRequest.setUserId(coinMsg.getUserId());
            coinRequest.setGoldCoin(coinMsg.getGoldCoin());
            coinRequest.setOrderNo(coinMsg.getOrderNo());
            coinRequest.setTradeTypeCode(coinMsg.getTradeTypeCode());
            coinRequest.setExt(coinMsg.getExt());
            SkUserTaskRecord record = skUserTaskRecordDAO.selectByOrderNo(coinRequest.getOrderNo());
            if(record==null) {
            	logger.error("未找到任何SkUserTaskRecord记录，OrderNo："+coinRequest.getOrderNo());
            	return;
            }
            
            //更改redis中的任务状态
            GetTaskCoinEvent getTaskCoinEvent = new GetTaskCoinEvent();
            getTaskCoinEvent.setTaskConfigId(record.getTaskId());
            getTaskCoinEvent.setUserId(record.getUserId());
            getTaskCoinEvent.setTimeInMilliseconds(System.currentTimeMillis());
			
            taskServiceImpl.getTaskCoinProcess(getTaskCoinEvent);
            
            
            BaseResponse response = accountingCenterProxy.syncGoldCoinFlow(coinRequest);
            //账户可能发生变动，清除账户缓存
            ClearRediskeyUtil.clearCachekey(coinMsg.getUserId());
            String tradeCode2 = null;

            int code2 = response.getCode();
            if (code2==0) {
                if (null != response.getData()) {
                    tradeCode2 = response.getData().toString();
                }
                record.setTradeNo(tradeCode2);
                record.setHandleState((byte) 1);
            } else {
                record.setHandleState((byte) 2);
            }
            record.setHandleRemark(JSON.toJSONString(response));
            record.setProcessCts(record.getProcessCts()+1);
            skUserTaskRecordDAO.updateByPrimaryKeySelective(record);
            
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
    }


}
