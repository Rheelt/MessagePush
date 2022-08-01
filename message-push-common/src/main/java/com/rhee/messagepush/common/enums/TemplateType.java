package com.rhee.messagepush.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author rhee
 * @date 2022/7/11 10:35 PM
 * 模板类型枚举
 */
@Getter
@ToString
@AllArgsConstructor
public enum TemplateType {
    //
    OPERATION(10, "运营类的模板"),
    TECHNOLOGY(20, "技术类的模板"),
    ;

    private Integer code;
    private String description;

}
