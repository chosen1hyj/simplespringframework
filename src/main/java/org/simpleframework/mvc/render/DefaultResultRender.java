package org.simpleframework.mvc.render;

import org.simpleframework.mvc.RequestProcessorChain;

/**
 * @description: 默认渲染器
 * @author: Chosen1
 * @date: 2022/2/7 18:57
 */
public class DefaultResultRender implements ResultRender{

    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws Exception {
        requestProcessorChain.getResponse().setStatus(requestProcessorChain.getResponseCode());

    }
}
