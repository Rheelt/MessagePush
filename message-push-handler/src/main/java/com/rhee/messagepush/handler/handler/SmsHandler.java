package com.rhee.messagepush.handler.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.rhee.messagepush.common.dto.SmsContentModel;
import com.rhee.messagepush.common.pojo.TaskInfo;
import com.rhee.messagepush.handler.domain.SmsParam;
import com.rhee.messagepush.handler.script.SmsScript;
import com.rhee.messagepush.support.dao.SmsRecordDao;
import com.rhee.messagepush.support.domain.SmsRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author rhee
 * @date 2022/7/17 4:45 PM
 */

@Component
public class SmsHandler implements Handler {
    @Autowired
    private SmsRecordDao smsRecordDao;

    @Autowired
    private SmsScript smsScript;

    @Override
    public boolean doHandler(TaskInfo taskInfo) {
        SmsContentModel smsContentModel = (SmsContentModel) taskInfo.getContentModel();

        String resultContent;
        if (StrUtil.isNotBlank(smsContentModel.getUrl())) {
            resultContent = smsContentModel.getContent() + " " + smsContentModel.getUrl();
        } else {
            resultContent = smsContentModel.getContent();
        }

        SmsParam smsParam = SmsParam.builder()
                .phones(taskInfo.getReceiver())
                .content(resultContent)
                .messageTemplateId(taskInfo.getMessageTemplateId())
                .supplierId(10)
                .supplierName("腾讯云通知类消息渠道").build();
        List<SmsRecord> recordList = smsScript.send(smsParam);

        if (CollUtil.isEmpty(recordList)) {
            return false;
        }

        smsRecordDao.saveAll(recordList);
        return true;
    }
}