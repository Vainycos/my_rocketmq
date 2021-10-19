package com.share.mymq.config;

/**
 * @author: Vainycos
 * @description
 * @date: 2021/10/12 9:24
 */
public class JmsConfig {

    /**
     * Name Server 地址，集群部署的话多个地址可以用分号隔开
     */
    public static final String NAME_SERVER = "127.0.0.1:9876";

    /**
     * 主题名称 主题一般是服务器设置好 而不能在代码里去新建topic（ 如果没有创建好，生产者往该主题发送消息 会报找不到topic错误）
     */
    public static final String TOPIC_SMS_SEND = "sms-send";
}
