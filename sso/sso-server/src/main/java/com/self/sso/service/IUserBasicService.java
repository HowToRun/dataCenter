package com.self.sso.service;

import com.self.basic.bean.vo.RequestParaInfo;
import com.self.basic.bean.vo.ResultContent;
import com.self.sso.entity.UserBasic;
import com.baomidou.mybatisplus.service.IService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mht
 * @since 2020-12-10
 */
public interface IUserBasicService extends IService<UserBasic> {

    ResultContent register(RequestParaInfo<UserBasic> request);

    ResultContent login(RequestParaInfo<UserBasic> request, String service, HttpServletRequest req, HttpServletResponse res) throws IOException;

    UserBasic verificationUserByToken(String token, String authorization);
}
