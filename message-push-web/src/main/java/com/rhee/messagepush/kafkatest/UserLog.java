package com.rhee.messagepush.kafkatest;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author rhee
 * @date 2022/7/24 11:35 AM
 * @Description 定义用户发送的日志数据
 */
@Data
@Accessors(chain = true)
public class UserLog {
    private String username;
    private String userid;
    private String state;
}