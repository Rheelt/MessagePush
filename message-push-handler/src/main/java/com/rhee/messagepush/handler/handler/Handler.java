package com.rhee.messagepush.handler.handler;

import com.rhee.messagepush.common.pojo.TaskInfo;

/**
 * @author rhee
 * @date 2022/7/11 10:48 PM
 */
public interface Handler {
    /**
     * 执行处理接口
     * */
    boolean doHandler(TaskInfo taskInfo);
}
