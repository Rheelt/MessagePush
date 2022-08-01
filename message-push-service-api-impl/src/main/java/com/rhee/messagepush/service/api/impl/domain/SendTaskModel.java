package com.rhee.messagepush.service.api.impl.domain;

import com.rhee.messagepush.common.pojo.TaskInfo;
import com.rhee.messagepush.service.api.domain.MessageParam;
import com.rhee.messagepush.support.pipeline.ProcessModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author rhee
 * @date 2022/7/30 4:26 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendTaskModel implements ProcessModel {


    /**
     * 消息模板Id
     */
    private Long messageTemplateId;

    /**
     * 请求参数
     */
    private List<MessageParam> messageParamList;

    /**
     * 发送任务的信息
     */
    private List<TaskInfo> taskInfo;




}
