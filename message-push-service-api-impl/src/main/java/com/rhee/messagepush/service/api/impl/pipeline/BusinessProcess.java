package com.rhee.messagepush.service.api.impl.pipeline;

/**
 * @author rhee
 * @date 2022/7/24 11:28 AM
 *
 * 业务执行器
 */
public interface BusinessProcess {

    /**
     * 业务处理
     * */
    void process(ProcessContext context);
}