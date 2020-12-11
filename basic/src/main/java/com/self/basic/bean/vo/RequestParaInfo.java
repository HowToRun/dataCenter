/**
 * Copyright (C), 2015 - 2020 , 北京和气聚力教育科技有限公司
 *
 * @fileName: RequestParaInfo
 * @author: huyih
 * @date: 2020/12/10 15:12
 * @description:
 * @history: <author>          <date>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.self.basic.bean.vo;

public class RequestParaInfo<T> {
//    todo 参数有待完善


    T param;
    String ticket;

    public T getParam() {
        return param;
    }

    public RequestParaInfo<T> setParam(T param) {
        this.param = param;
        return this;
    }

    public String getTicket() {
        return ticket;
    }

    public RequestParaInfo<T> setTicket(String ticket) {
        this.ticket = ticket;
        return this;
    }

    @Override
    public String toString() {
        return "RequestParaInfo{" +
                "param=" + param +
                ", ticket='" + ticket + '\'' +
                '}';
    }
}
