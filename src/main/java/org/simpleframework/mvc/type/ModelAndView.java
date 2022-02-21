package org.simpleframework.mvc.type;

import lombok.Getter;
import lombok.extern.flogger.Flogger;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 存储处理完后的结果数据, 以及显示该数据的视图
 * @author: Chosen1
 * @date: 2022/2/7 23:08
 */
public class ModelAndView {

    //页面所在的路径
    @Getter
    private String view;
    //页面的data数据
    @Getter
    private Map<String, Object> model = new HashMap<>();

    public ModelAndView setView(String view){
        this.view = view;
        return this;
    }

    public ModelAndView addViewData(String attributeName, Object attributeValue){
        model.put(attributeName, attributeValue);
        return this;
    }

}
