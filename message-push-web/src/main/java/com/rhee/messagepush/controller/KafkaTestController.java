package com.rhee.messagepush.controller;

import com.rhee.messagepush.kafkatest.UserLogProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rhee
 * @date 2022/7/24 11:40 AM
 */

@RestController
public class KafkaTestController {

    @Autowired
    private UserLogProducer userLogProducer;

    /**
     * test insert
     */
    @GetMapping("/kafka")
    public String insert(String userId) {
        System.out.println(userId);
        userLogProducer.sendLog(userId);

        return null;
    }

}