package com.rhee.messagepush.common.pojo;


import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * @author rhee
 * @date 2022/7/11 10:36 PM
 */

@Data
@Builder
public class SmsParam {
    /**
     * 业务Id
     */
    private Long messageTemplateId;

    /**
     * 需要发送的手机号
     */
    private Set<String> phones;

    /**
     * 发送文案
     */
    private String content;

    /**
     * 渠道商Id
     */
    private Integer supplierId;

    /**
     * 渠道商名字
     */
    private String supplierName;
}
