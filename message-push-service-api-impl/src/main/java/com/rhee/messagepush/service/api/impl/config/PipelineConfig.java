package com.rhee.messagepush.service.api.impl.config;

import com.rhee.messagepush.service.api.enums.BusinessCode;
import com.rhee.messagepush.service.api.impl.action.AssembleAction;
import com.rhee.messagepush.service.api.impl.action.PreParamAction;
import com.rhee.messagepush.service.api.impl.action.SendMqAction;
import com.rhee.messagepush.support.pipeline.BusinessProcess;
import com.rhee.messagepush.support.pipeline.ProcessController;
import com.rhee.messagepush.support.pipeline.ProcessTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rhee
 * @date 2022/8/1 11:00 PM
 */
@Configuration
public class PipelineConfig {


    /**
     * 普通发送执行流程
     * 1. 参数校验
     * 2. 组装参数
     * 3. 发送消息至MQ
     * @return
     */
    @Bean("commonSendTemplate")
    public ProcessTemplate commonSendTemplate() {
        ProcessTemplate processTemplate = new ProcessTemplate();
        ArrayList<BusinessProcess> processList = new ArrayList<>();

        processList.add(preParamAction());
        processList.add(assembleAction());
        processList.add(sendMqAction());

        processTemplate.setProcessList(processList);
        return processTemplate;
    }

    /**
     * pipeline流程控制器
     * 目前暂定只有 普通发送的流程
     * 后续扩展则加BusinessCode和ProcessTemplate
     * @return
     */
    @Bean
    public ProcessController processController() {
        ProcessController processController = new ProcessController();
        Map<String, ProcessTemplate> templateConfig = new HashMap<>();
        templateConfig.put(BusinessCode.COMMON_SEND.getCode(), commonSendTemplate());
        processController.setTemplateConfig(templateConfig);
        return processController;
    }


    /**
     * 组装参数Action
     * @return
     */
    @Bean
    public AssembleAction assembleAction() {
        return new AssembleAction();
    }

    /**
     * 参数校验Action
     * @return
     */
    @Bean
    public PreParamAction preParamAction() {
        return new PreParamAction();
    }

    /**
     * 发送消息至MQ的Action
     * @return
     */
    @Bean
    public SendMqAction sendMqAction() {
        return new SendMqAction();
    }

}
