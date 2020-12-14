package com.self.datadic.query;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * SQL过滤
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-01 16:16
 */
public class SQLFilter {

  /**
   * SQL注入过滤
   *
   * @param str 待验证的字符串
   */
  public static String sqlInject(String str) {
    if (StringUtils.isBlank(str)) {
      return null;
    }

    String[] exclusions = {"update_time"};  // 例外
    if(Arrays.asList(exclusions).contains(str)){
      return str;
    }

    //去掉'|"|;|\字符
    str = StringUtils.replace(str, "'", "");
    str = StringUtils.replace(str, "\"", "");
    str = StringUtils.replace(str, ";", "");
    str = StringUtils.replace(str, "\\", "");

    //转换成小写
//    str = str.toLowerCase();

    //非法字符
    String[] keywords = {"master", "truncate", "insert", "select", "delete", "declare",
        "alert", "drop"};

    //判断是否包含非法字符
    for (String keyword : keywords) {
      if (str.indexOf(keyword) != -1) {
        throw new RuntimeException("包含非法字符:"+str);
      }
    }

    return str;
  }

  /**
   * 通过反射对字符串类型进行sql注入拦截。
   * @param o
   * @return
   */
  public static Object sqlFilterBean(Object o) {
    String[] exclusions = {"jdbcUrl","username","password","name","tableName"};  // 例外
    try {
      Class<?> clazz = o.getClass();
      for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
          if (!Arrays.asList(exclusions).contains(f.getName())&&f.getType().getName().equals("java.lang.String")) {
            f.setAccessible(true);
            Object value = f.get(o);
            if (!ObjectUtils.isEmpty(value)) {
              f.set(o, SQLFilter.sqlInject(value.toString()));
            }
          }
        }
      }

    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return o;
  }
}
