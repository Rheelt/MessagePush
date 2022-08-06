package com.rhee.messagepush.support.pipeline;

import com.rhee.messagepush.common.vo.BasicResultVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author rhee
 * @date 2022/7/24 11:29 AM
 *
 * 责任链上下文
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class ProcessContext {

    /**
     * 标识责任链的code
     * 通过此code来确定责任链的执行流程，参见PipelineConfig
     * 此code和SendRequest的code是相关联，通过不同的请求调用不同的Pipeline
     */
    private String code;

    /**
     * 责任链需要执行的数据的上下文
     */
    private ProcessModel processModel;

    /**
     * 责任链中断的标识
     */
    private Boolean needBreak;

    /**
     * 流程处理的结果
     */
    BasicResultVO response;

}