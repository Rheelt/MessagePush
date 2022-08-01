package com.rhee.messagepush.support.pipeline;

import java.util.List;

/**
 * @author rhee
 * @date 2022/7/24 11:29 AM
 * <p>
 * 业务执行模板（把责任链的逻辑串起来）
 */
public class ProcessTemplate {

    private List<BusinessProcess> processList;

    public List<BusinessProcess> getProcessList() {
        return processList;
    }

    public void setProcessList(List<BusinessProcess> processList) {
        this.processList = processList;
    }
}