package org.simpleframework.mvc.processor;

import org.simpleframework.mvc.RequestProcessorChain;

/**
 * @description: 请求执行器
 * @author: Chosen1
 * @date: 2022/2/7 18:07
 */

public interface RequestProcessor {

    boolean process(RequestProcessorChain requestProcessorChain) throws Exception;
}
