///**
// * Copyright (C), 2015 - 2020 , 北京和气聚力教育科技有限公司
// *
// * @fileName: ChangeAnnotation
// * @author: huyih
// * @date: 2020/12/14 14:37
// * @description:
// * @history: <author>          <date>          <version>          <desc>
// * 作者姓名           修改时间           版本号              描述
// */
//package com.self.datadic.annotation;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//import java.util.Map;
//
//public  class ChangeAnnotation {
//    public static void change(String table){
//        String annotation = table;
//        try {
//            //获取UserServiceImpl类下所有方法
//            Method[]methods = UserServiceImpl.class.getMethods();
//            int i= 0;
//            //判断方法上是否有注解DS
//            while (methods[i].getAnnotation(DynamicDB.class)!=null){
//                DynamicDB ds = methods[i].getAnnotation(DynamicDB.class);
//                InvocationHandler invocationHandler = Proxy.getInvocationHandler(ds);
//                Field value = invocationHandler.getClass().getDeclaredField("memberValues");
//                value.setAccessible(true);
//                Map<String, Object> memberValues = (Map<String, Object>) value.get(invocationHandler);
//                String val = (String) memberValues.get("value");
//                System.out.println("改变前：" + val);
//                val = annotation;
//                memberValues.put("value", val);
//                System.out.println("改变后：" + ds.dataSource());
//                i++;
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}
