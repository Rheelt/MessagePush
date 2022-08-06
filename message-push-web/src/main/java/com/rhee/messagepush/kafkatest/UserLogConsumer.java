package com.rhee.messagepush.kafkatest;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author rhee
 * @date 2022/7/24 11:35 AM
 */
@Component
@Slf4j
public class UserLogConsumer {


    //           topicPartitions = @TopicPartition(topic = "quickstart",
//                    partitionOffsets = {
//                            @PartitionOffset(partition = "0", initialOffset = "0")
//                    })
    @KafkaListener(topics = {"quickstart"}, groupId = "testGroup")
    public void consumer(ConsumerRecord<?, ?> consumerRecord) {
        System.err.println("consumer in");
        //判断是否为null
        Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        log.info(">>>>>>>>>> record =" + kafkaMessage);
        if (kafkaMessage.isPresent()) {
            //得到Optional实例中的值
            Object message = kafkaMessage.get();
            System.err.println("消费消息:" + message);
        }
    }
}