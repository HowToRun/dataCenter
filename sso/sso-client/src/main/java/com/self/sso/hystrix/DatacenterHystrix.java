/**
 * Copyright (C), 2015 - 2020 , 北京和气聚力教育科技有限公司
 *
 * @fileName: DatacenterHystrix
 * @author: huyih
 * @date: 2020/10/16 17:15
 * @description:
 * @history: <author>          <date>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.self.sso.hystrix;

import com.self.sso.fegin.SSOFeign;
import org.springframework.stereotype.Component;

@Component
public class DatacenterHystrix implements SSOFeign {

    @Override
    public String getPayment(Integer id) {
        return "请求超时了";
    }
}
