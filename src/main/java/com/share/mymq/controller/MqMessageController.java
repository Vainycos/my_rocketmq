package com.share.mymq.controller;

import com.share.mymq.DO.SmsInfoDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.share.mymq.config.JmsConfig.TOPIC_SMS_SEND;

/**
 * @author: Vainycos
 * @description RocketMQ消息队列服务
 * @date: 2021/10/11 18:45
 */
@Slf4j
@RestController
@RequestMapping("/mq")
public class MqMessageController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 同步发送
     * @param req
     * @return
     */
    @GetMapping("/syncGo")
    public boolean syncGo(SmsInfoDO req) {
        log.info("[短信发送生产者-同步]开始推送给mq......");
        rocketMQTemplate.syncSend(TOPIC_SMS_SEND, req);
        log.info("[短信发送生产者-同步]已推送给mq");
        return true;
    }

    /**
     * 异步发送
     * @param req
     * @return
     */
    @GetMapping("/asyncGo")
    public boolean asyncGo(SmsInfoDO req){
        rocketMQTemplate.asyncSend(TOPIC_SMS_SEND, req, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("[短信发送生产者-异步]已被mq消费!{}", sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                log.info("[短信发送生产者-异步]发送失败!{}", throwable.getMessage());
            }
        });
        return true;
    }

    /**
     * 采用one-way发送模式发送消息
     * 存在消息丢失的风险，所以其适用于不重要的消息发送，比如日志收集
     * @param req
     * @return
     */
    @GetMapping("/oneWayGo")
    public boolean oneWayGo(SmsInfoDO req){
        log.info("[短信发送生产者-one-way]开始推送给mq......");
        rocketMQTemplate.sendOneWay(TOPIC_SMS_SEND, req);
        log.info("[短信发送生产者-one-way]已推送给mq");
        return true;
    }

}
