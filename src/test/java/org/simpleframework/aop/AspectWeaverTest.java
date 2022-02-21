package org.simpleframework.aop;

import com.hyj.controller.frontend.MainPageController;
import com.hyj.controller.superadmin.HeadLineOperationController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.simpleframework.core.annotation.BeanContainer;
import org.simpleframework.inject.DependencyInjector;

/**
 * @description:
 * @author: Chosen1
 * @date: 2021/10/7 14:10
 */
public class AspectWeaverTest {

    @DisplayName("织入通用逻辑测试: doAop")
    @Test
    public void doAopTest(){
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("com.imooc");
        new AspectWeaver().doAop();
        new DependencyInjector().doIoc();
        HeadLineOperationController headLineOperationController = (HeadLineOperationController) beanContainer.getBean(HeadLineOperationController.class);
        headLineOperationController.addHeadLine(null, null);
        MainPageController mainPageController = (MainPageController) beanContainer.getBean(MainPageController.class);
        mainPageController.getMainPageInfo(null, null);
    }
}
