package com.hyj;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: Chosen1
 * @date: 2021/8/24 22:38
 */

@WebServlet("/hello")
@Slf4j
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = "我的简易框架";
        log.debug("hhhhh");
        req.setAttribute("name", name);
        req.getRequestDispatcher("WEB-INF/jsp/hello.jsp").forward(req, resp);
    }
}
