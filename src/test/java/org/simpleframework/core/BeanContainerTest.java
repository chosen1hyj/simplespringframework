package org.simpleframework.core;

import org.simpleframework.mvc.DispatcherServlet;
import com.hyj.controller.frontend.MainPageController;
import com.hyj.service.solo.HeadLineService;
import com.hyj.service.solo.impl.HeadLineServiceImpl;
import org.junit.jupiter.api.*;
import org.simpleframework.core.annotation.BeanContainer;
import org.simpleframework.core.annotation.Controller;

/**
 * @description:
 * @author: Chosen1
 * @date: 2022/2/5 20:39
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BeanContainerTest {

    private static BeanContainer beanContainer;

    @BeforeAll
    static void init(){
        beanContainer = BeanContainer.getInstance();
    }

    @DisplayName("加载目标类及其实例到BeanContainer: loadBeansTest")
    @Order(1)
    @Test
    public void loadBeansTest(){
        Assertions.assertEquals(false, beanContainer.isLoaded());
        beanContainer.loadBeans("com.imooc");
        Assertions.assertEquals(6, beanContainer.size());
        Assertions.assertEquals(true, beanContainer.isLoaded());
    }

    @DisplayName("根据类获取实例: getBeanTest")
    @Order(2)
    @Test
    public void getBeanTest(){
        MainPageController controller = (MainPageController) beanContainer.getBean(MainPageController.class);
        Assertions.assertEquals(true, controller instanceof MainPageController);
        DispatcherServlet servlet = (DispatcherServlet) beanContainer.getBean(DispatcherServlet.class);
        Assertions.assertEquals(null, servlet);
    }

    @DisplayName("根据注解获取对应的实例: getClassesByAnnotationTest")
    @Order(3)
    @Test
    public void getClassesByAnnotationTest(){
        Assertions.assertEquals(true, beanContainer.isLoaded());
        Assertions.assertEquals(3, beanContainer.getClassByAnnotation(Controller.class).size());
    }

    @DisplayName("根据注解获取对应的实例: getClassesBySuperTest")
    @Order(4)
    @Test
    public void getClassesBySuperTest(){
        Assertions.assertEquals(true, beanContainer.isLoaded());
        Assertions.assertEquals(true, beanContainer.getClassBySuper(HeadLineService.class).contains(HeadLineServiceImpl.class));
    }


}
