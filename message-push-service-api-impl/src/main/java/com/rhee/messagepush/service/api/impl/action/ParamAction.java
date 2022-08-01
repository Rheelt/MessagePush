package com.rhee.messagepush.service.api.impl.action;

import com.rhee.messagepush.service.api.impl.domain.SendTaskModel;
import com.rhee.messagepush.support.pipeline.BusinessProcess;
import com.rhee.messagepush.support.pipeline.ProcessContext;

/**
 * @author rhee
 * @date 2022/7/30 4:25 PM
 */
public class ParamAction implements BusinessProcess {
    @Override
    public void process(ProcessContext context) {
        SendTaskModel sendTaskModel = (SendTaskModel) context.getProcessModel();
    }
}
