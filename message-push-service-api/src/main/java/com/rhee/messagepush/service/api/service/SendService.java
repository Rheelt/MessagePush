package com.rhee.messagepush.service.api.service;

import com.rhee.messagepush.service.api.domain.SendRequest;
import com.rhee.messagepush.service.api.domain.SendResponse;

/**
 * @author rhee
 * @date 2022/7/24 10:57 AM
 * 发送接口
 */
public interface SendService {


    SendResponse send(SendRequest sendRequest);


    SendResponse batchSend(SendRequest sendRequest);
}

