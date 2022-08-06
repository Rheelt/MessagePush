package com.rhee.messagepush.service.api.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * @author rhee
 * @date 2022/7/30 4:21 PM
 * 发送信息具体内容
 */
@Data
@Accessors(chain = true)
public class MessageParam {

    /**
     * @Description: 接收者
     * 多个用,逗号号分隔开
     * 必传
     */
    private String receiver;

    /**
     * @Description: 消息内容中的可变部分
     * 可选
     */
    private Map<String, String> variables;

    /**
     * @Description: 扩展参数
     * 可选
     */
    private Map<String,String> extra;
}