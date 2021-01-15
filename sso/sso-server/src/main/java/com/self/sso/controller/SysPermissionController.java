package com.self.sso.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.self.basic.bean.vo.RequestParaInfo;
import com.self.basic.bean.vo.ResultContent;
import com.self.basic.constant.BasicConstant;
import com.self.sso.entity.SysPermission;
import com.self.sso.service.ISysPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Mht
 * @since 2021-01-15
 */
@RestController
@RequestMapping("/sysPermission")
public class SysPermissionController {

    private Logger logger = LoggerFactory.getLogger(SysPermissionController.class);

    @Resource
    ISysPermissionService sysPermissionService;

    @RequestMapping(value = "/saveOrUpdate")
    public ResultContent getPayment(@RequestBody RequestParaInfo<SysPermission> requestParaInfo) {
        SysPermission param = requestParaInfo.getParam();
        param.setAvailable(BasicConstant.USE);
        boolean b = sysPermissionService.insertOrUpdate(param);
        if (b) {
            return ResultContent.createNoDataSuccessResult();
        } else {
            return ResultContent.createNoDataErrorResult();
        }
    }
    @RequestMapping(value = "/list")
    public ResultContent list(@RequestBody RequestParaInfo<SysPermission> requestParaInfo) {
        SysPermission param = requestParaInfo.getParam();
        String type = param.getType();
        Wrapper<SysPermission> wrapper = new EntityWrapper<>();
        wrapper.eq("type", type);
        wrapper.eq("available", BasicConstant.USE);
        List<SysPermission> sysPermissions = sysPermissionService.selectList(wrapper);
        return ResultContent.createSuccessResult(sysPermissions);
    }
    @RequestMapping(value = "/delPermission")
    public ResultContent delPermission(@RequestBody RequestParaInfo<SysPermission> requestParaInfo) {
        SysPermission param = requestParaInfo.getParam();
        Long id = param.getId();

        boolean b = sysPermissionService.deleteById(id);
        if (b) {
            return ResultContent.createNoDataSuccessResult();
        } else {
            return ResultContent.createNoDataErrorResult();
        }
    }
    @RequestMapping(value = "/stopPermission")
    public ResultContent stopPermission(@RequestBody RequestParaInfo<SysPermission> requestParaInfo) {
        SysPermission param = requestParaInfo.getParam();
        Long id = param.getId();
        param.setAvailable(BasicConstant.NO_USE);
        boolean b = sysPermissionService.updateById(param);
        if (b) {
            return ResultContent.createNoDataSuccessResult();
        } else {
            return ResultContent.createNoDataErrorResult();
        }
    }

}
