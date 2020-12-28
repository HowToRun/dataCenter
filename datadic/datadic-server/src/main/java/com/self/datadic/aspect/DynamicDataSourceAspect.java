package com.self.datadic.aspect;

import com.self.datadic.annotation.DynamicDB;
import com.self.datadic.config.SpringContextConfig;
import com.self.datadic.dynamic.DataSourceBean;
import com.self.datadic.dynamic.DataSourceContext;
import com.self.datadic.dynamic.DataSourceUtil;
import com.self.datadic.query.ExtTablebaseInfoQuery;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-1)// 保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {

  private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);


//    /**
//     * 定义一个切点
//     */
//    @Pointcut("@annotation(com.self.datadic.annotation.DynamicDB)")
//    public void cutOffPoint() {
//    }
    /**
     * 定义一个切点
     */
    @Pointcut("execution(* com.self.datadic.service.impl.*.*(..))")
    public void cutOffPoint() {
    }


  @Before(value = "@annotation(db)")
  public void changeDataSource(JoinPoint point, DynamicDB db) {
      System.out.println("apo before");
      System.out.println(db);
    DataSourceBean bean = null;
    boolean isdefaultDataSource = false;
    if (StringUtils.isBlank(db.dataSource())) {
        ExtTablebaseInfoQuery info = (ExtTablebaseInfoQuery) point.getArgs()[0];
        bean = DataSourceUtil.getDataSourceById(info);
        //hive serviceimpl 获取connection
        info.setJdbcUrl(bean.getUrl());
        info.setUsername(bean.getUsername());
        info.setPassword(bean.getPassword());
        info.setName(bean.getBeanName());
    }else if (db.dataSource().equals("defaultDataSource")){
        /**
         *
         * 解决来回切换数据源后，要切回默认数据源后，无法切回默认数据源
         * 使用方式,在调用的事物方法上加如下参数
         *  @Transactional(propagation =Propagation.REQUIRES_NEW)
            @DynamicDB(dataSource = "defaultDataSource")

         *  详细见TErEntitySnapshootServiceImpl.java 类
         */
        isdefaultDataSource = true;
    } else {
        bean = (DataSourceBean) SpringContextConfig.getBean(db.dataSource());
    }


    DataSourceContext.setDataSource(bean);
    if(isdefaultDataSource){
        logger.debug(">>>>>>>>>>>>>>>>切换默认数据源>>>>>>>>");
    }else{
        logger.debug(">>>>>>>>>Use DataSource : 数据源Key:{} > 返回值:{} !", bean.getBeanName(),
                bean.toString());
        System.out.println(">>>>>>>>>Use DataSource : 数据源Key:{} > 返回值:{} !"+ bean.getBeanName()+"===="
                + bean.toString());
    }

  }

  @After("@annotation(db)")
  public void restoreDataSource(JoinPoint point, DynamicDB db) {
      System.out.println("aop after");
    DataSourceContext.clearDataSource();
    logger.debug("<<<<<<<<DataSource return default !",
        point.getSignature());
  }
}
