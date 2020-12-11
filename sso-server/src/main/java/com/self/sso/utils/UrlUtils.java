/**
 * Copyright (C), 2015 - 2020 , 北京和气聚力教育科技有限公司
 *
 * @fileName: UrlUtils
 * @author: huyih
 * @date: 2020/12/11 14:50
 * @description:
 * @history: <author>          <date>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.self.sso.utils;

import com.self.sso.constant.SSOConstant;

public class UrlUtils {

    public static String addSTToUrl(String serviceUrl,String st){
        if (serviceUrl.contains("?")){
            return serviceUrl+"&ticket="+st;
        }else {
            return serviceUrl+"?ticket="+st;
        }
    }
    public static String addLoginServiceToUrl(String serviceUrl){
        return SSOConstant.DEFAULT_CO_LOGIN_JUMP+"?service="+serviceUrl;
    }

}
