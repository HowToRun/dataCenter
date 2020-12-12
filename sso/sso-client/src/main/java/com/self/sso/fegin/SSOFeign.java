/**
 * Copyright (C), 2015 - 2020 , 北京和气聚力教育科技有限公司
 *
 * @fileName: DatadicFeign
 * @author: huyih
 * @date: 2020/10/16 17:14
 * @description:
 * @history: <author>          <date>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.self.sso.fegin;

import com.self.sso.hystrix.DatacenterHystrix;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-provider",fallback = DatacenterHystrix.class)
@Qualifier(value="service-provider")
public interface SSOFeign {
    @RequestMapping("/datadic/payment/nacos")
    String getPayment(@RequestParam("id") Integer id);
}
