package com.rhee.messagepush.service.api.domain;

/**
 * @author rhee
 * @date 2022/7/24 10:56 AM
 * 发送接口的参数
 */

public class SendRequest {

    /**
     * 消息模板Id
     */
    private Long messageTemplateId;

    /**
     * 接收者
     * 多个用
     */
    private String receiver;



}
