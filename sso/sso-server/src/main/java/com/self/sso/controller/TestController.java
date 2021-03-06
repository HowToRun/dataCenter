/**
 * Copyright (C), 2015 - 2020 , 北京和气聚力教育科技有限公司
 *
 * @fileName: TestController
 * @author: huyih
 * @date: 2020/10/16 17:18
 * @description:
 * @history: <author>          <date>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.self.sso.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.self.sso.feign.DatadicFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RefreshScope
public class TestController {
    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @Qualifier("service-provider")
    @Autowired
    DatadicFeign datadicFeign;

    @RequestMapping(value = "/testLogger")
    public String getPayment(@RequestParam("id") Integer id) {
        logger.info("SSO testController logger");
        return "SSO testController logger";
    }
}
