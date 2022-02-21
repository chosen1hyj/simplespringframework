package com.hyj.controller.frontend;

import com.hyj.entity.dto.MainPageInfoDTO;
import com.hyj.entity.dto.Result;
import com.hyj.service.combine.HeadLineShopCategoryCombineService;
import lombok.Getter;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.inject.annotation.Autowired;
import org.simpleframework.mvc.annotation.RequestMapping;
import org.simpleframework.mvc.type.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: Chosen1
 * @date: 2022/2/4 18:57
 */

@Controller
@Getter
@RequestMapping("/main")
public class MainPageController {

    @Autowired
    private HeadLineShopCategoryCombineService headLineShopCategoryCombineService;
    public Result<MainPageInfoDTO> getMainPageInfo(HttpServletRequest req, HttpServletResponse resp){
        return headLineShopCategoryCombineService.getMainPageInfo();
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void throwException(){
        System.out.println("hhhh");
        throw new RuntimeException("抛出异常测试");
    }
}
