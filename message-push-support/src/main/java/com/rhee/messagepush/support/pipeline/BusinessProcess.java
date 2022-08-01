package com.rhee.messagepush.support.pipeline;

/**
 * @author rhee
 * @date 2022/7/24 11:28 AM
 *
 * 业务执行器
 */
public interface BusinessProcess {

    /**
     * 真正处理逻辑
     * @param context
     */
    void process(ProcessContext context);
}