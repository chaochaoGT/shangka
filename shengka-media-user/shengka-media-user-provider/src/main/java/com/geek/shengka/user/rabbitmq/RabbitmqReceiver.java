package com.geek.shengka.user.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.esotericsoftware.minlog.Log;
import com.geek.shengka.common.enums.UserBaseTypeEnum;
import com.geek.shengka.common.mq.UserBaseDataMsg;
import com.geek.shengka.user.entity.vo.SkUserBaseInfoVO;
import com.geek.shengka.user.mapper.SkUserBaseInfoDAO;
import com.geek.shengka.user.utils.BeanMapperUtils;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yunfei
 */
@Service
public class RabbitmqReceiver {

    public final static Logger logger = LoggerFactory.getLogger(RabbitmqReceiver.class);


    @Autowired
    private SkUserBaseInfoDAO skUserBaseInfoDAO;

    /**
     * 消费者， 维护用户冗余基础数据
     * @param message
     * @param channel
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "userBaseDataQueue",
                    durable = "true"),
            exchange = @Exchange(value = "userBaseDataXchange",
                    durable = "true",
                    type = "topic",
                    ignoreDeclarationExceptions = "true"),
            key = "userBaseData.sync"
    ))
    @RabbitHandler
    public void receiverUserActionMsg(Message<String> message, Channel channel) {
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        try {
            channel.basicAck(deliveryTag, false);
            UserBaseDataMsg coinMsg = JSON.parseObject(message.getPayload(),
                    new TypeReference<UserBaseDataMsg>() {
                    });
            logger.info("rabbitmq监听处理完对象:" + JSON.toJSONString(coinMsg));
            String code = UserBaseTypeEnum.getValue(coinMsg.getCode());
            if(code==null||coinMsg.getUserId()==null){
                return;
            }
            Map<String, Object> map = new ConcurrentHashMap<>(2);
            map.put(code, coinMsg.getNum());
            map.put("userId", coinMsg.getUserId());
            int num = skUserBaseInfoDAO.updateUserBaseNum(map);
            /**针对乐观锁 修改数量超出库里的值 增加脏数据悲观处理机制  一层保险*/
            if(num==0){
                SkUserBaseInfoVO center = skUserBaseInfoDAO.center(coinMsg.getUserId());
                if(center==null){
                    return;
                }
                Map beanMap = BeanMapperUtils.map(center, Map.class);
                int count = beanMap.get(code)==null?0:Integer.parseInt(beanMap.get(code).toString());
                int data= count<Math.abs(coinMsg.getNum())?(0-count):coinMsg.getNum();
                map.put(code, data);
               skUserBaseInfoDAO.updateUserBaseNum(map);
            }
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }

    }

    public static void main(String[] args) {
        SkUserBaseInfoVO vo=new SkUserBaseInfoVO();
        vo.setNearestNum(100);
        Map beanMap = BeanMapperUtils.map(vo, Map.class);
        Object num = beanMap.get("nearestNum");
    }

}
