package com.rhee.messagepush.support.pipeline;

import lombok.Builder;

/**
 * @author rhee
 * @date 2022/7/30 4:30 PM
 */
@Builder
public class ProcessResponse {

    /** 返回值编码 */
    private final String code;

    /** 返回值描述 */
    private final String description;

}
