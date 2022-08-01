package com.rhee.messagepush.service.api.impl.service;

import com.rhee.messagepush.common.vo.BasicResultVO;
import com.rhee.messagepush.service.api.domain.BatchSendRequest;
import com.rhee.messagepush.service.api.domain.SendRequest;
import com.rhee.messagepush.service.api.domain.SendResponse;
import com.rhee.messagepush.service.api.impl.domain.SendTaskModel;
import com.rhee.messagepush.service.api.service.SendService;
import com.rhee.messagepush.support.pipeline.ProcessContext;
import com.rhee.messagepush.support.pipeline.ProcessController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author rhee
 * @date 2022/7/24 11:32 AM
 */
@Service
public class SendServiceImpl implements SendService  {

    @Autowired
    private ProcessController processController;

    @Override
    public SendResponse send(SendRequest sendRequest) {

        SendTaskModel sendTaskModel = SendTaskModel.builder()
                .messageTemplateId(sendRequest.getMessageTemplateId())
                .messageParamList(Arrays.asList(sendRequest.getMessageParam()))
                .build();

        ProcessContext context = ProcessContext.builder()
                .code(sendRequest.getCode())
                .processModel(sendTaskModel)
                .needBreak(false)
                .response(BasicResultVO.success()).build();

        ProcessContext process = processController.process(context);

        return new SendResponse(process.getResponse().getCode(), process.getResponse().getMsg());
    }

    @Override
    public SendResponse batchSend(BatchSendRequest batchSendRequest) {
        SendTaskModel sendTaskModel = SendTaskModel.builder()
                .messageTemplateId(batchSendRequest.getMessageTemplateId())
                .messageParamList(batchSendRequest.getMessageParamList())
                .build();

        ProcessContext context = ProcessContext.builder()
                .code(batchSendRequest.getCode())
                .processModel(sendTaskModel)
                .needBreak(false)
                .response(BasicResultVO.success()).build();

        ProcessContext process = processController.process(context);

        return new SendResponse(process.getResponse().getCode(), process.getResponse().getMsg());
    }


}
