package com.rhee.messagepush.service.api.impl.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author rhee
 * @date 2022/7/30 4:26 PM
 */
@Getter
@ToString
@AllArgsConstructor
public enum RequestType {

    // 见description
    SINGLE(10, "请求接口为 single 类型"),
    BATCH(20, "请求接口为 batch 类型");

    /**
     * code
     */
    private Integer code;

    /**
     * 类型说明
     */
    private String description;
}
