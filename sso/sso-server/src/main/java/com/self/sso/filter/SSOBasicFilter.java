///**
// * Copyright (C), 2015 - 2020 , 北京和气聚力教育科技有限公司
// *
// * @fileName: SSOBasicFilter
// * @author: huyih
// * @date: 2020/12/11 15:28
// * @description:
// * @history: <author>          <date>          <version>          <desc>
// * 作者姓名           修改时间           版本号              描述
// */
//package com.self.sso.filter;
//
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class SSOBasicFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        String requestURI = request.getRequestURI();
//        System.out.println("TestFilter,"+ requestURI);
//
//        //执行
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
