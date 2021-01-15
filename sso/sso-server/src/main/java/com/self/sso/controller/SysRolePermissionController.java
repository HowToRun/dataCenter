package com.self.sso.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Mht
 * @since 2021-01-15
 */
@RestController
@RequestMapping("/sysRolePermission")
public class SysRolePermissionController {
    private Logger logger = LoggerFactory.getLogger(SysRolePermissionController.class);
}
