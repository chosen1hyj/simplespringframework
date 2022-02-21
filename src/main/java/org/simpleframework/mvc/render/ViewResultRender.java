package org.simpleframework.mvc.render;

import org.simpleframework.mvc.RequestProcessorChain;
import org.simpleframework.mvc.type.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @description: 页面选渲染器
 * @author: Chosen1
 * @date: 2022/2/7 19:00
 */
public class ViewResultRender implements ResultRender{
    public static final String VIEW_PATH = "/templates/";
    private ModelAndView modelAndView;

    /**
     * 对传入的参数进行处理, 并赋值给ModelAndView成员变量
     * @param mv
     */
    public ViewResultRender(Object mv) {
        //1. 如果传入参数类型是ModelAndView,则直接赋值给成员变量
        if(mv instanceof ModelAndView){
            this.modelAndView = (ModelAndView) mv;
        }else if(mv instanceof String){
            //2. 如果传入参数类型是String,则为视图,需要包装后才能赋值给成员变量
            this.modelAndView = new ModelAndView().setView((String) mv);
        }else{
            //3. 针对其他情况,直接抛出异常
            throw new RuntimeException("illegal request result type");
        }

    }

    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws Exception {
        HttpServletRequest request = requestProcessorChain.getRequest();
        HttpServletResponse response = requestProcessorChain.getResponse();
        String path = modelAndView.getView();
        Map<String, Object> model = modelAndView.getModel();
        for (Map.Entry<String, Object> entry : model.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
        //JSP
        request.getRequestDispatcher(VIEW_PATH + path).forward(request, response);
    }
}
