package com.rhee.messagepush.kafkatest;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author rhee
 * @date 2022/7/24 11:36 AM
 */
@Component
public class UserLogProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 发送数据
     */
    public void sendLog(String userid){
        UserLog userLog = new UserLog();
        userLog.setUsername("test").setUserid(userid).setState("0");
        System.err.println("发送用户日志数据:"+userLog);
        kafkaTemplate.send("quickstart", JSON.toJSONString(userLog));
    }
}