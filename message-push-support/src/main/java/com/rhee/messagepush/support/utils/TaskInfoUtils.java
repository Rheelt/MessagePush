package com.rhee.messagepush.support.utils;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author rhee
 * @date 2022/7/30 6:21 PM
 */
public class TaskInfoUtils {

    private static int TYPE_FLAG = 1000000;

    /**
     * 生成BusinessId
     * 模板类型+模板ID+当天日期
     * (固定16位)
     */
    public static Long generateBusinessId(Long templateId, Integer templateType) {
        Integer today = Integer.valueOf(DateUtil.format(new Date(), "yyyyMMdd"));
        return Long.valueOf(String.format("%d%s", templateType * TYPE_FLAG + templateId, today));
    }

}