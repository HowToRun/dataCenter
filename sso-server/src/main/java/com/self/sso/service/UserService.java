/**
 * Copyright (C), 2015-2020, 北京和气聚力教育科技有限公司
 *
 * @fileName: UserService
 * @author: yihan.hu
 * @date: 2020/12/10 15:19
 * @description:
 * @history: <author>          <date>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.self.sso.service;

import com.self.basic.bean.vo.RequestParaInfo;
import com.self.basic.bean.vo.ResultContent;
import com.self.sso.entity.UserBasic;

public interface UserService {
    ResultContent register(RequestParaInfo<UserBasic> request);
}
