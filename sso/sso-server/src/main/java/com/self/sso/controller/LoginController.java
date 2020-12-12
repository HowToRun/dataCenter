/**
 * Copyright (C), 2015 - 2020 , 北京和气聚力教育科技有限公司
 *
 * @fileName: LoginController
 * @author: huyih
 * @date: 2020/12/9 14:54
 * @description:
 * @history: <author>          <date>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.self.sso.controller;

import com.self.basic.bean.vo.RequestParaInfo;
import com.self.basic.bean.vo.ResultContent;
import com.self.sso.constant.SSOConstant;
import com.self.sso.entity.UserBasic;
import com.self.sso.service.IUserBasicService;
import com.self.sso.utils.UrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class LoginController {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);


    @Resource
    private IUserBasicService userBasicService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultContent getPayment(@RequestBody RequestParaInfo<UserBasic> request,
                                    @CookieValue(value = "token",defaultValue = SSOConstant.EMPTY_STRING) String token,
                                    @RequestHeader(value = "authorization",defaultValue = SSOConstant.EMPTY_STRING)String authorization,
                                    @RequestParam(defaultValue = SSOConstant.DEFAULT_LOGIN_JUMP) String service,
                                    HttpServletRequest req,
                                    HttpServletResponse res) throws IOException {
        LOGGER.info("SSO ======> user login");
        LOGGER.info("SSO ======> token ======>"+token);
        LOGGER.info("SSO ======> authorization ======>"+authorization);
        UserBasic userBasic = userBasicService.verificationUserByToken(token, authorization);
        if (userBasic!=null){
            res.sendRedirect(service);
            return ResultContent.createSuccessResult("");
        }
        return userBasicService.login(request,service,req,res);
    }

    @RequestMapping(value = "/loginSuccess",method = RequestMethod.GET)
    public String loginSuccess(@CookieValue(value = "token",defaultValue = SSOConstant.EMPTY_STRING) String token,
                                      @RequestHeader(value = "authorization",defaultValue = SSOConstant.EMPTY_STRING)String authorization) throws IOException {
        return "token======>"+token+"\nauthorization======>"+authorization;

    }
    @RequestMapping(value = "/verificationUserByToken",method = RequestMethod.POST)
    public ResultContent verificationUserByToken(@RequestBody RequestParaInfo<UserBasic> request,
                               @CookieValue(value = "token",defaultValue = SSOConstant.EMPTY_STRING) String token,
                               @RequestHeader(value = "authorization",defaultValue = SSOConstant.DEFAULT_CO_LOGIN_JUMP)String authorization,
                               @RequestParam(defaultValue = SSOConstant.DEFAULT_LOGIN_JUMP) String service,
                               HttpServletRequest req,
                               HttpServletResponse res) throws IOException {
        UserBasic userBasic = userBasicService.verificationUserByToken(token, authorization);
        if (userBasic==null){
            String redirectUrl = UrlUtils.addLoginServiceToUrl(service);
            res.sendRedirect(redirectUrl);
        }
        return ResultContent.createSuccessResult(userBasic);
    }

    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    public ResultContent getUserInfo(@CookieValue(value = "token",defaultValue = SSOConstant.EMPTY_STRING) String token,
                               @RequestHeader(value = "authorization",defaultValue = SSOConstant.EMPTY_STRING)String authorization){
        UserBasic userBasic = userBasicService.verificationUserByToken(token, authorization);
        return ResultContent.createSuccessResult(userBasic);
    }


}
