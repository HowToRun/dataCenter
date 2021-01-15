/**
 * Copyright (C), 2015 - 2020 , 北京和气聚力教育科技有限公司
 *
 * @fileName: HTTPResultContent
 * @author: huyih
 * @date: 2020/12/10 14:44
 * @description:
 * @history: <author>          <date>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.self.basic.bean.vo;

import com.self.basic.constant.BasicConstant;

import java.util.Objects;

public class ResultContent<T> {
    private Integer code;
    private T data;
    private String message;

    public static <T>ResultContent<T> createResult(Integer code, T data,String msg){
        return new ResultContent<>(code,data,msg);
    }
    public static <T>ResultContent<T> createSuccessResult(T data){
        return new ResultContent<>(BasicConstant.SUCCESS_CODE,data);
    }
    public static <T>ResultContent<T> createNoDataSuccessResult(){
        return new ResultContent<>(BasicConstant.SUCCESS_CODE,null,BasicConstant.SUCCESS_MSG);
    }

    public static <T>ResultContent<T> createSuccessResult(T data,String msg){
        return new ResultContent<>(BasicConstant.SUCCESS_CODE,data,msg);
    }
    public static <T>ResultContent<T> createSuccessResult(String msg){
        return new ResultContent<>(BasicConstant.SUCCESS_CODE,msg);
    }
    public static <T>ResultContent<T> createErrorResult(T data,String msg){
        return new ResultContent<>(BasicConstant.ERROR_CODE,data,msg);
    }
    public static <T>ResultContent<T> createNoDataErrorResult(){
        return new ResultContent<>(BasicConstant.ERROR_CODE,null,BasicConstant.ERROR_MSG);
    }
    public static <T>ResultContent<T> createErrorResult(String msg){
        return new ResultContent<>(BasicConstant.ERROR_CODE,msg);
    }

    public ResultContent() {
    }

    public ResultContent(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultContent(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResultContent(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public ResultContent<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResultContent<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultContent<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultContent<?> that = (ResultContent<?>) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(data, that.data) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, data, message);
    }

    @Override
    public String toString() {
        return "ResultContent{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
