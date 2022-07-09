package com.rhee.message.script;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * @author rhee
 * @date 2022/7/9 10:54 PM
 */

@Data
@Builder
public class SmsParam {
    /**
     * 需要发送的手机号
     */
    private Set<String> phones;

    /**
     * 发送文案
     */
    private String content;
}
