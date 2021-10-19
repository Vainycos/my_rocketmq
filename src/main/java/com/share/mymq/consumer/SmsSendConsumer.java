package com.share.mymq.consumer;

import com.share.mymq.DO.SmsInfoDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import static com.share.mymq.config.JmsConfig.TOPIC_SMS_SEND;


/**
 * @author: Vainycos
 * @description 短信服务 消费者
 * @date: 2021/10/12 17:22
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = TOPIC_SMS_SEND, consumerGroup = "sms-group")
public class SmsSendConsumer implements RocketMQListener<SmsInfoDO> {

    @Override
    public void onMessage(SmsInfoDO smsInfoDO) {
        log.info("[短信发送消费者]模拟发送，发送中......{}", smsInfoDO);
        // 发送短信业务逻辑 ...
        log.info("[短信发送消费者]发送成功");
    }
}
