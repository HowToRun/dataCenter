/**
 * Copyright (C), 2015 - 2020 , 北京和气聚力教育科技有限公司
 *
 * @fileName: UserController
 * @author: huyih
 * @date: 2020/12/10 14:36
 * @description:
 * @history: <author>          <date>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.self.sso.controller;

import com.self.basic.bean.vo.RequestParaInfo;
import com.self.basic.bean.vo.ResultContent;
import com.self.sso.entity.UserBasic;
import com.self.sso.service.IUserBasicService;
import com.self.sso.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;
    @Resource
    private IUserBasicService userBasicService;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResultContent getPayment(@RequestBody RequestParaInfo<UserBasic> request) {
        LOGGER.info("SSO ======> user register");

        return userBasicService.register(request);
    }
}
