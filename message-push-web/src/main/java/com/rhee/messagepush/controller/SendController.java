package com.rhee.messagepush.controller;

import com.rhee.messagepush.common.pojo.TaskInfo;
import com.rhee.messagepush.common.vo.BasicResultVO;
import com.rhee.messagepush.handler.handler.SmsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashSet;

/**
 * @author rhee
 * @date 2022/7/17 10:15 PM
 */
@RestController
public class SendController {

    @Autowired
    private SmsHandler smsHandler;

    /**
     * 测试发送短信
     *
     * @param phone 手机号
     * @return BasicResultVO
     */
    @GetMapping("/sendSms")
    public BasicResultVO<Void> sendSms(String phone, String content, Long messageTemplateId) {

        TaskInfo taskInfo = TaskInfo.builder().receiver(new HashSet<>(
                        Collections.singletonList(phone)))
                .content(content)
                .messageTemplateId(messageTemplateId)
                .build();

        if (smsHandler.doHandler(taskInfo)) {
            return BasicResultVO.success("发送信息成功");
        }

        return BasicResultVO.fail();
    }

}