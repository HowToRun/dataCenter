/**
 * Copyright (C), 2015 - 2020 , 北京和气聚力教育科技有限公司
 *
 * @fileName: UserServiceImpl
 * @author: huyih
 * @date: 2020/12/10 15:19
 * @description:
 * @history: <author>          <date>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.self.sso.service.impl;

import com.self.basic.bean.vo.RequestParaInfo;
import com.self.basic.bean.vo.ResultContent;
import com.self.sso.entity.UserBasic;
import com.self.sso.constant.SSOConstant;
import com.self.sso.service.UserService;
import com.self.sso.utils.PassWordUtil;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public ResultContent register(RequestParaInfo<UserBasic> request) {
        UserBasic param = request.getParam();
        String userName = param.getUserName();
        String saltMD5 = PassWordUtil.getSaltMD5(SSOConstant.DEFAULT_PASSWORD);
        return null;
    }
}
