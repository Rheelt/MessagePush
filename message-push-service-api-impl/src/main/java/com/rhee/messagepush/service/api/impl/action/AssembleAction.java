package com.rhee.messagepush.service.api.impl.action;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Throwables;
import com.rhee.messagepush.common.constant.ProjectConstant;
import com.rhee.messagepush.common.dto.ContentModel;
import com.rhee.messagepush.common.enums.ChannelType;
import com.rhee.messagepush.common.enums.RespStatusEnum;
import com.rhee.messagepush.common.pojo.TaskInfo;
import com.rhee.messagepush.common.vo.BasicResultVO;
import com.rhee.messagepush.service.api.domain.MessageParam;
import com.rhee.messagepush.service.api.impl.domain.SendTaskModel;
import com.rhee.messagepush.support.dao.MessageTemplateDao;
import com.rhee.messagepush.support.domain.MessageTemplate;
import com.rhee.messagepush.support.pipeline.BusinessProcess;
import com.rhee.messagepush.support.pipeline.ProcessContext;
import com.rhee.messagepush.support.utils.ContentHolderUtil;
import com.rhee.messagepush.support.utils.TaskInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author rhee
 * @date 2022/7/30 4:25 PM
 */
@Slf4j
public class AssembleAction implements BusinessProcess {

    @Autowired
    private MessageTemplateDao messageTemplateDao;

    @Override
    public void process(ProcessContext context) {
        SendTaskModel sendTaskModel = (SendTaskModel) context.getProcessModel();
        Long messageTemplateId = sendTaskModel.getMessageTemplateId();

        try {
            Optional<MessageTemplate> messageTemplate = messageTemplateDao.findById(messageTemplateId);
            if (!messageTemplate.isPresent() || messageTemplate.get().getIsDeleted().equals(ProjectConstant.TRUE)) {
                context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.TEMPLATE_NOT_FOUND));
                return;
            }

            List<TaskInfo> taskInfos = assembleTaskInfo(sendTaskModel, messageTemplate.get());
            sendTaskModel.setTaskInfo(taskInfos);
        } catch (Exception e) {
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.SERVICE_ERROR));
            log.error("assemble task fail! templateId:{}, e:{}", messageTemplateId, Throwables.getStackTraceAsString(e));
        }

    }

    /**
     * 组装 TaskInfo 任务消息
     *
     * @param sendTaskModel
     * @param messageTemplate
     */
    private List<TaskInfo> assembleTaskInfo(SendTaskModel sendTaskModel, MessageTemplate messageTemplate) {
        List<MessageParam> messageParamList = sendTaskModel.getMessageParamList();
        List<TaskInfo> taskInfoList = new ArrayList<>();

        for (MessageParam messageParam : messageParamList) {
            TaskInfo taskInfo = TaskInfo.builder()
                    .messageTemplateId(messageTemplate.getId())
                    .businessId(TaskInfoUtils.generateBusinessId(messageTemplate.getId(), messageTemplate.getTemplateType()))
                    .receiver(new HashSet<>(Arrays.asList(messageParam.getReceiver().split(String.valueOf(StrUtil.C_COMMA)))))
                    .idType(messageTemplate.getIdType())
                    .sendChannel(messageTemplate.getSendChannel())
                    .templateType(messageTemplate.getTemplateType())
                    .msgType(messageTemplate.getMsgType())
                    .sendAccount(messageTemplate.getSendAccount())
                    .contentModel(getContentModelValue(messageTemplate, messageParam))
                    .deduplicationTime(messageTemplate.getDeduplicationTime())
                    .isNightShield(messageTemplate.getIsNightShield()).build();

            taskInfoList.add(taskInfo);
        }

        return taskInfoList;

    }


    /**
     * 获取 contentModel,替换占位符信息
     */
    private static ContentModel getContentModelValue(MessageTemplate messageTemplate, MessageParam messageParam) {
        Integer sendChannel = messageTemplate.getSendChannel();
        Map<String, String> variables = messageParam.getVariables();
        JSONObject jsonObject = JSON.parseObject(messageTemplate.getMsgContent());
        Class contentModelClass = ChannelType.getChanelModelClassByCode(sendChannel);


        /**
         *  反射获取得到不同的渠道对应的值
         */
        Field[] fields = ReflectUtil.getFields(contentModelClass);
        ContentModel contentModel = (ContentModel) ReflectUtil.newInstance(contentModelClass);
        for (Field field : fields) {
            String originValue = jsonObject.getString(field.getName());

            if (StrUtil.isNotBlank(originValue)) {
                String resultValue = ContentHolderUtil.replacePlaceHolder(originValue, variables);
                ReflectUtil.setFieldValue(contentModel, field, resultValue);
            }
        }

        return contentModel;
    }

    public static void main(String[] args) {

        MessageTemplate messageTemplate = MessageTemplate.builder().sendChannel(ChannelType.SMS.getCode()).msgContent("{\"url\":\"www.baidu.com/{$urlParam}\",\"content\":\"{$contentValue}\"}").build();
        HashMap<String, String> map = new HashMap<>();
        map.put("urlParam", "2222");
        map.put("contentValue", "3333");
        MessageParam messageParam = new MessageParam().setVariables(map);

        ContentModel contentModelValue = getContentModelValue(messageTemplate, messageParam);
        System.out.println(JSON.toJSONString(contentModelValue));
    }
}

