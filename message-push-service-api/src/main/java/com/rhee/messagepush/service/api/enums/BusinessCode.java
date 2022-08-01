package com.rhee.messagepush.service.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author rhee
 * @date 2022/7/30 4:23 PM
 */
@Getter
@ToString
@AllArgsConstructor
public enum BusinessCode {
    // 枚举的含义见description
    COMMON_SEND("send", "普通发送"),
    RECALL("recall", "撤回消息");


    /** code 关联着责任链的模板 */
    private String code;

    /** 类型说明 */
    private String description;


}
