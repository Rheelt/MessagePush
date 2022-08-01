package com.rhee.messagepush.handler;

import com.alibaba.fastjson.JSON;
import com.rhee.messagepush.common.pojo.TaskInfo;
import com.rhee.messagepush.handler.handler.SmsHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author rhee
 * @date 2022/7/30 4:44 PM
 * 消费MQ的消息
 */
@Component
@Slf4j
public class Receiver {

    @Autowired
    private SmsHandler smsHandler;

    @KafkaListener(topics = {"austin"}, groupId = "sms")
    public void consumer(ConsumerRecord<?, String> consumerRecord) {
        Optional<String> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        if (kafkaMessage.isPresent()) {
            List<TaskInfo> lists = JSON.parseArray(kafkaMessage.get(), TaskInfo.class);
            for (TaskInfo taskInfo : lists) {
                smsHandler.doHandler(taskInfo);
            }
            log.info("receiver message:{}", JSON.toJSONString(lists));
        }

    }

}
