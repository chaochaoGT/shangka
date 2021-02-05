package com.geek.shengka.content.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.esotericsoftware.minlog.Log;
import com.geek.shengka.common.enums.UserActionEventEnum;
import com.geek.shengka.common.proxy.ContentVideoInfoProxy;
import com.geek.shengka.common.request.ContextReportRequest;
import com.geek.shengka.content.entity.SkTopicVideo;
import com.geek.shengka.content.mapper.SkTopicConfigDAO;
import com.geek.shengka.content.mapper.SkTopicVideoDAO;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 消费者
 *
 * @author: yunfei
 * @create: 2019-08-07 14:14
 **/
@Component
public class RabbitmqReceiver {

    public final static Logger logger = LoggerFactory.getLogger(RabbitmqReceiver.class);

    @Autowired
    private ContentVideoInfoProxy contentVideoInfoProxy;

    @Autowired
    private SkTopicVideoDAO skTopicVideoDAO;

    @Autowired
    private SkTopicConfigDAO skTopicConfigDAO;


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "contentReportQueue",
                    durable = "true"),
            exchange = @Exchange(value = "contentReportXchange",
                    durable = "true",
                    type = "topic",
                    ignoreDeclarationExceptions = "true"),
            key = "contentReport.*"
    ))
    @RabbitHandler
    public void receiverContentReport(Message<String> message, Channel channel) {
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        try {
            channel.basicAck(deliveryTag, false);
            ContextReportRequest request = JSON.parseObject(message.getPayload(),
                    new TypeReference<ContextReportRequest>() {
                    });
            logger.info("rabbitmq监听处理完对象:" + JSON.toJSONString(request));
            contentVideoInfoProxy.userBehaviorReported(request);
            if (UserActionEventEnum.play_start.getType() == request.getType()) {
                updateTopicWatchCount(request.getVideoId());
            }
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
    }

    private void updateTopicWatchCount(String videoId) {
        List<SkTopicVideo> list = skTopicVideoDAO.selectByVideoId(videoId);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        List<Long> collect = list.stream().map(SkTopicVideo::getTopicId).distinct().collect(Collectors.toList());
        skTopicConfigDAO.updateWatchNum(collect);
    }

}
