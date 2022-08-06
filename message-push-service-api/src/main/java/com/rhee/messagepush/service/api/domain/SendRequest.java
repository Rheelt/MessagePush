package com.rhee.messagepush.service.api.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author rhee
 * @date 2022/7/24 10:56 AM
 * 发送接口的参数
 */
@Data
@Accessors(chain = true)
public class SendRequest {

    /**
     * 执行业务类型
     * 必传,参考 BusinessCode枚举
     * 表示请求类型
     */
    private String code;

    /**
     * 消息模板Id
     */
    private Long messageTemplateId;


    /**
     * 消息相关的参数
     */
    private MessageParam messageParam;



}
