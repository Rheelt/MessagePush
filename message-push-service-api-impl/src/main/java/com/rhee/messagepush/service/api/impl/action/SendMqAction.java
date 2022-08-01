package com.rhee.messagepush.service.api.impl.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.base.Throwables;
import com.rhee.messagepush.common.enums.RespStatusEnum;
import com.rhee.messagepush.common.vo.BasicResultVO;
import com.rhee.messagepush.service.api.impl.domain.SendTaskModel;
import com.rhee.messagepush.support.pipeline.BusinessProcess;
import com.rhee.messagepush.support.pipeline.ProcessContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author rhee
 * @date 2022/8/1 11:00 PM
 * 将消息发送到MQ
 */
@Slf4j
public class SendMqAction implements BusinessProcess {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value("${austin.topic.name}")
    private String topicName;

    @Override
    public void process(ProcessContext context) {
        SendTaskModel sendTaskModel = (SendTaskModel) context.getProcessModel();
        try {
            kafkaTemplate.send(topicName, JSON.toJSONString(sendTaskModel.getTaskInfo(),
                    new SerializerFeature[] {SerializerFeature.WriteClassName}));
        } catch (Exception e) {
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.SERVICE_ERROR));
            log.error("send kafka fail! e:{}", Throwables.getStackTraceAsString(e));
        }
    }
}
