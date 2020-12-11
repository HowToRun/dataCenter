package com.self.sso.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.self.basic.bean.vo.RequestParaInfo;
import com.self.basic.bean.vo.ResultContent;
import com.self.sso.constant.SSOConstant;
import com.self.sso.entity.UserBasic;
import com.self.sso.mapper.UserBasicMapper;
import com.self.sso.service.IUserBasicService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.self.sso.utils.CookieUtils;
import com.self.sso.utils.PassWordUtil;
import com.self.sso.utils.UrlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Mht
 * @since 2020-12-10
 */
@Service
public class UserBasicServiceImpl extends ServiceImpl<UserBasicMapper, UserBasic> implements IUserBasicService {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public ResultContent register(RequestParaInfo<UserBasic> request) {
        UserBasic param = request.getParam();
        String userName = param.getUserName();
        UserBasic userBasic = getUserByUserName(userName);
        if (userBasic != null) {
            return ResultContent.createErrorResult("用户名已存在");
        }
        String saltMD5 = PassWordUtil.getSaltMD5(SSOConstant.DEFAULT_PASSWORD);
        param.setPassword(saltMD5);
        param.setCreateTime(new Date());
//        todo 当前用户
        param.setCreateUser(SSOConstant.SYSTEM_USER);
        this.insert(param);
        return ResultContent.createSuccessResult("success");
    }

    private UserBasic getUserByUserName(String userName) {
        Wrapper<UserBasic> wrapper = new EntityWrapper<>();
        wrapper.eq("user_name", userName);
        return this.selectOne(wrapper);
    }

    @Override
    public ResultContent login(RequestParaInfo<UserBasic> request, String service, HttpServletRequest req, HttpServletResponse res) throws IOException {
        UserBasic param = request.getParam();
        String userName = param.getUserName();
        String password = param.getPassword();
        UserBasic userBasic = getUserByUserName(userName);
        if (userBasic == null) return ResultContent.createErrorResult("用户名或密码错误");

        String passwordWithMD5 = userBasic.getPassword();
        boolean loginResult = PassWordUtil.getSaltverifyMD5(password, passwordWithMD5);
        if (!loginResult) return ResultContent.createErrorResult("用户名或密码错误");


        String userBasicJsonStr = JSONObject.toJSONString(userBasic);
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(token, userBasicJsonStr);
        CookieUtils.setCookie(res, "token", token);
        String redirectUrl = UrlUtils.addSTToUrl(service, token);
        res.sendRedirect(redirectUrl);
        return ResultContent.createSuccessResult(token, "登陆成功");
//        String str = (String) redisTemplate.opsForValue().get("service");


    }

    @Override
    public UserBasic verificationUserByToken(String token, String authorization) {
        if (!(StringUtils.isNotBlank(token) || StringUtils.isNotBlank(authorization))) {
//            信息携带错误
            return null;
        }
        authorization = authorization.replace(SSOConstant.BEARER_STRING, SSOConstant.EMPTY_STRING);
        token = StringUtils.isNotBlank(token) ? token : authorization;
        String userBasicInfoStr = (String) redisTemplate.opsForValue().get(token);
        if (StringUtils.isBlank(userBasicInfoStr)) {
            return null;
        }
        UserBasic userBasic = JSONObject.parseObject(userBasicInfoStr, UserBasic.class);
//        UserBasic userBasic = selectById(1336956850713096194L);
        return userBasic;
    }
}
