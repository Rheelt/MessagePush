package com.rhee.messagepush.service.api.impl.action;

import cn.hutool.core.collection.CollUtil;
import com.rhee.messagepush.common.enums.RespStatusEnum;
import com.rhee.messagepush.common.vo.BasicResultVO;
import com.rhee.messagepush.service.api.domain.MessageParam;
import com.rhee.messagepush.service.api.impl.domain.SendTaskModel;
import com.rhee.messagepush.support.pipeline.BusinessProcess;
import com.rhee.messagepush.support.pipeline.ProcessContext;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author rhee
 * @date 2022/8/1 10:59 PM
 * 前置参数校验
 */
@Slf4j
public class PreParamAction implements BusinessProcess {

    @Override
    public void process(ProcessContext context) {
        SendTaskModel sendTaskModel = (SendTaskModel) context.getProcessModel();

        Long messageTemplateId = sendTaskModel.getMessageTemplateId();
        List<MessageParam> messageParamList = sendTaskModel.getMessageParamList();

        if (messageTemplateId == null || CollUtil.isEmpty(messageParamList)) {
            context.setNeedBreak(true);
            context.setResponse(BasicResultVO.fail(RespStatusEnum.CLIENT_BAD_PARAMETERS));
        }
    }
}
