/**
 * Copyright (C), 2015 - 2020 , 北京和气聚力教育科技有限公司
 *
 * @fileName: SSOBasicFilter
 * @author: huyih
 * @date: 2020/12/11 15:28
 * @description:
 * @history: <author>          <date>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.self.sso.filter;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SSOBasicFilter implements Filter {

    @Value("#{'${sso.fiter.skip-urls: }'.split(',')} ")
    List<String> skipUrl;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        System.out.println("TestFilter,"+ requestURI);
        boolean contains = containsSkipUrl(requestURI);
        if (!contains){
//            todo 验证是否登录
        }
        //执行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean containsSkipUrl(String requestURI) {
//        有/*则放开所有路径
        if (skipUrl.contains("/*")) return true;
//        先完全匹配
        boolean contains = skipUrl.contains(requestURI);
        if (contains) return contains;
//        存在/xxx/*的情况
        List<String> collect = skipUrl.stream().filter(e -> e.contains("/*"))
                .map(e->e.replace("/*",""))
                .collect(Collectors.toList());
        String[] splitUrl = requestURI.split("/");
        for (String s : splitUrl) {
            if (!StringUtils.isNotEmpty(s)) continue;
            for (String skip : collect) {
                if (skip.endsWith(s)) return true;
            }
        }

        return false;
    }

    @Override
    public void destroy() {

    }
}
