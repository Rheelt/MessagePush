package com.rhee.messagepush;

import com.rhee.messagepush.script.SmsParam;
import com.rhee.messagepush.script.TencentSmsScript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;

/**
 * @author rhee
 * @date 2022/7/2 10:10 PM
 */
@SpringBootApplication
@RestController
public class MessagePushApplication {

    @Autowired
    private TencentSmsScript tencentSmsScript;

    @RequestMapping("/home")
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/send")
    String sendSms() {
        SmsParam smsParam = SmsParam.builder()
                .phones(new HashSet<>(List.of("17628046040")))
                .content("test")
                .build();

        return tencentSmsScript.send(smsParam);
    }

    public static void main(String[] args) {
        SpringApplication.run(MessagePushApplication.class, args);
    }
}
