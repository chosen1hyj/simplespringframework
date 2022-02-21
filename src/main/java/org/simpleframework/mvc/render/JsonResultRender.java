package org.simpleframework.mvc.render;

import com.google.gson.Gson;
import org.simpleframework.mvc.RequestProcessorChain;

import java.io.PrintWriter;

/**
 * @description: Json渲染器
 * @author: Chosen1
 * @date: 2022/2/7 18:58
 */


public class JsonResultRender implements ResultRender{

    private Object jsonData;


    public JsonResultRender(Object jsonData) {
        this.jsonData = jsonData;
    }

    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws Exception {
        //设置响应头
        requestProcessorChain.getResponse().setContentType("application/json");
        requestProcessorChain.getResponse().setCharacterEncoding("UTF-8");
        //响应流写入经过gson格式化后的处理结果
        try(PrintWriter writer = requestProcessorChain.getResponse().getWriter()){
            Gson gson = new Gson();
            writer.write(gson.toJson(jsonData));
            writer.flush();
        }
    }
}
