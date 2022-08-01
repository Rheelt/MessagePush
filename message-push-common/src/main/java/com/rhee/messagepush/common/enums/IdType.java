package com.rhee.messagepush.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author rhee
 * @date 2022/7/11 10:32 PM
 * 发送ID类型枚举
 */
@Getter
@ToString
@AllArgsConstructor
public enum IdType {
    //
    USER_ID(10, "userId"),
    DID(20, "did"),
    PHONE(30, "phone"),
    OPEN_ID(40, "openId"),
    EMAIL(50, "email");

    private Integer code;
    private String description;

}
