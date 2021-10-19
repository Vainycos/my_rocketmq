package com.share.mymq.DO;

import lombok.Data;

/**
 * @author: Vainycos
 * @description 短信发送实体
 * @date: 2021/10/15 17:04
 */
@Data
public class SmsInfoDO {

    /**
     * 接收人姓名
     */
    private String name;

    /**
     * 接收人号码
     */
    private String phone;


}
