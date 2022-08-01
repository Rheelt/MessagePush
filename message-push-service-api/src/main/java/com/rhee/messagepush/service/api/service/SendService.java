package com.rhee.messagepush.service.api.service;

import com.rhee.messagepush.service.api.domain.BatchSendRequest;
import com.rhee.messagepush.service.api.domain.SendRequest;
import com.rhee.messagepush.service.api.domain.SendResponse;

/**
 * @author rhee
 * @date 2022/7/24 10:57 AM
 * 发送接口
 */
public interface SendService {



    /**
     * 单文案发送接口
     * @param sendRequest
     * @return
     */
    SendResponse send(SendRequest sendRequest);


    /**
     * 多文案发送接口
     * @param batchSendRequest
     * @return
     */
    SendResponse batchSend(BatchSendRequest batchSendRequest);

}

