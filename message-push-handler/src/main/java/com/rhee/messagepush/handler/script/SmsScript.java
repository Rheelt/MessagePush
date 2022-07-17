package com.rhee.messagepush.handler.script;

import com.rhee.messagepush.common.pojo.SmsParam;
import com.rhee.messagepush.support.domain.SmsRecord;

import java.util.List;

/**
 * @author rhee
 * @date 2022/7/17 4:40 PM
 */
public interface SmsScript {
    /**
     * @param smsParam 发送短信参数
     * @return 渠道商接口返回值
     */
    List<SmsRecord> send(SmsParam smsParam);
}
