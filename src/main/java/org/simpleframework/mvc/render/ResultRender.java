package org.simpleframework.mvc.render;

import org.simpleframework.mvc.RequestProcessorChain;

/**
 * @description:
 * @author: Chosen1
 * @date: 2022/2/7 18:38
 */
public interface ResultRender {

    void render(RequestProcessorChain requestProcessorChain) throws Exception;
}
