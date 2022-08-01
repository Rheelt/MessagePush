package com.rhee.messagepush.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author rhee
 * @date 2022/7/11 10:33 PM
 * 发送消息类型枚举
 */
@Getter
@ToString
@AllArgsConstructor
public enum MessageType {
    //
    NOTICE(10,"通知类消息"),
    MARKETING(20,"营销类消息"),
    AUTH_CODE(30,"验证码消息")
    ;

    /**
     *     `msg_type`           tinyint(4)
     *     NOT NULL DEFAULT '0' COMMENT '10.通知类消息 20.营销类消息 30.验证码类消息',
     */

    private Integer code;
    private String description;

}
