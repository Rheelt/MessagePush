package com.rhee.messagepush.kafkatest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author rhee
 * @date 2022/7/24 11:35 AM
 */
@Component
@Slf4j
public class UserLogConsumer {

//    @KafkaListener(topics = {"quickstart"}, groupId = "testGroup")
//    partitionOffsets = {
//        @PartitionOffset(partition = "0", initialOffset = "0")
//    }

//    @KafkaListener(groupId = "testGroup", topicPartitions = @TopicPartition(topic = "quickstart", partitions = {"0", "1"}))
//    public void consumer(ConsumerRecord<?, ?> consumerRecord) {
//        System.err.println("consumer in");
//        //判断是否为null
//        Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
//        log.info(">>>>>>>>>> record =" + kafkaMessage);
//        if (kafkaMessage.isPresent()) {
//            //得到Optional实例中的值
//            Object message = kafkaMessage.get();
//            System.err.println("消费消息:" + message);
//        }
//    }

    @KafkaListener(topics = "quickstart", groupId = "test-consumer")
    public void listen(@Payload String message,
                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        log.info("接收消息: {}，partition：{}", message, partition);
    }

}