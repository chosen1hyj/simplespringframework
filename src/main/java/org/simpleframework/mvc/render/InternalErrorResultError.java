package org.simpleframework.mvc.render;

import org.simpleframework.mvc.RequestProcessorChain;

import javax.servlet.http.HttpServletResponse;

/**
 * @description: 内部异常渲染器
 * @author: Chosen1
 * @date: 2022/2/7 19:02
 */
public class InternalErrorResultError implements ResultRender{

    private String errorMsg;

    public InternalErrorResultError(String errorMsg){
        this.errorMsg = errorMsg;
    }
    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws Exception {
        requestProcessorChain.getResponse().sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMsg);
    }
}
