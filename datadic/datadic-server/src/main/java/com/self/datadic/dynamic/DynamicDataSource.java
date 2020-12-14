package com.self.datadic.dynamic;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuefeng.shang
 */
@Configuration
public class DynamicDataSource extends AbstractRoutingDataSource implements
        ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

    private static final String DATA_SOURCES_NAME = "targetDataSources";

    private ApplicationContext applicationContext;

    // 默认数据源
    private DataSource defaultDataSource;

    private PropertyValues dataSourcePropertyValues;

    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceBean dataSourceBean = DataSourceContext.getDataSource();
        if (dataSourceBean == null) {
            return null;
        }

        try {
            createDataSource(dataSourceBean);
            return dataSourceBean.getBeanName();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("multi datasource switch exception");
        }
    }


    private void createDataSource(DataSourceBean dataSourceBean) throws Exception {
        //在spring容器中创建并且声明bean
        ConfigurableApplicationContext context = (ConfigurableApplicationContext) applicationContext;
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
                .genericBeanDefinition(DruidDataSource.class);
        //将dataSourceBean中的属性值赋给目标beaninitialSize
        Map<String, Object> properties = getPropertyKeyValues(DataSourceBean.class, dataSourceBean);
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            beanDefinitionBuilder.addPropertyValue((String) entry.getKey(), entry.getValue());
        }
        //todo 目前是每次创建多个连接，有性能问题，需要后续调整。故此注释掉。

        //-----下面设置为了dataSource连接失败一次就结束  start -----
        beanDefinitionBuilder.addPropertyValue("connectionErrorRetryAttempts", "0");
        beanDefinitionBuilder.addPropertyValue("timeBetweenConnectErrorMillis", "10000");
        beanDefinitionBuilder.addPropertyValue("breakAfterAcquireFailure", true);
        //-----下面设置为了dataSource连接失败一次就结束  end -----

//    beanDefinitionBuilder.addPropertyValue("initialSize", "1");
//    beanDefinitionBuilder.addPropertyValue("minIdle", "1");//最开始设置1，但是多用户访问可能会有卡；如果设置过大会导致数据库连接太多。故此设置5个。
//    beanDefinitionBuilder.addPropertyValue("maxActive", "10");
//    beanDefinitionBuilder.addPropertyValue("maxWait", "60000");
//    beanDefinitionBuilder.addPropertyValue("minEvictableIdleTimeMillis", "300000");
        /**
         * 设置没有可用链接时最大的等到时间
         */
        beanDefinitionBuilder.addPropertyValue("maxWait", "10000");
       /* beanDefinitionBuilder.addPropertyValue("validationQuery", "SELECT 1");
        beanDefinitionBuilder.addPropertyValue("validationQueryTimeout", "1000");*/


        //-----下面设置,如果不配置使用默认值，回导致sqlserver 连接不上（性能问题） ，其他数据库类型不受影响  start -----
        beanDefinitionBuilder.addPropertyValue("testWhileIdle", "true");//默认false
        beanDefinitionBuilder.addPropertyValue("testOnBorrow", "false");//默认true
        beanDefinitionBuilder.addPropertyValue("testOnReturn", "false");//默认false
        //添加回收机制后
        beanDefinitionBuilder.addPropertyValue("removeAbandoned", true);//默认false
        //-----下面设置,如果不配置使用默认值，回导致sqlserver 连接不上 ，其他数据库类型不受影响  end -----

//    beanDefinitionBuilder.addPropertyValue("poolPreparedStatements", "true");
//    beanDefinitionBuilder.addPropertyValue("maxPoolPreparedStatementPerConnectionSize", "20");
        //属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有： 监控统计用的filter:stat日志用的filter:log4j防御sql注入的filter:wall
        //beanDefinitionBuilder.addPropertyValue("filters", "stat");

        //相同的bean 不再创建
        if (beanFactory.containsBeanDefinition(dataSourceBean.getBeanName())
                && beanFactory.getBeanDefinition(dataSourceBean.getBeanName()).equals(beanDefinitionBuilder.getBeanDefinition()))
        {
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>.datasourceBean is already exist,dont create datasourceBean!");
        }
        else
        {
            beanFactory.registerBeanDefinition(dataSourceBean.getBeanName(),
                    beanDefinitionBuilder.getBeanDefinition());

            Map<Object, Object> map = getTargetDataSources();
            synchronized (map) {
                // 判断是否是默认数据源。
                if (!"defaultDataSource".equals(dataSourceBean.getBeanName())) {
                    map.put(dataSourceBean.getBeanName(), applicationContext.getBean(dataSourceBean.getBeanName()));
                    super.afterPropertiesSet();//通知spring有bean更新
                }
            }
        }


    }

    private Map<Object, Object> getTargetDataSources()
            throws NoSuchFieldException, IllegalAccessException {
        Field field = AbstractRoutingDataSource.class.getDeclaredField(DATA_SOURCES_NAME);
        field.setAccessible(true);
        return (Map<Object, Object>) field.get(this);
    }


    private <T> Map<String, Object> getPropertyKeyValues(Class<T> clazz, Object object)
            throws IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();
        Map<String, Object> result = new HashMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            result.put(field.getName(), field.get(object));
        }
        result.remove("beanName");
        result.remove("type");
        return result;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        //初始化默认数据源
        this.initDateSource(applicationContext.getEnvironment());
        this.setDefaultTargetDataSource(defaultDataSource);
        Map map = new HashMap();
        map.put("defaultDataSource", defaultDataSource);
        this.setTargetDataSources(map);
    }


    @Value("${spring.datasource.type:com.zaxxer.hikari.HikariDataSource}")
    String type;

    @Value("${spring.datasource.driver-class-name:com.mysql.jdbc.Driver}")
    String driverClassName;
    @Value("${spring.datasource.url:jdbc:mysql://localhost:3306/datacenter_data_dic?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&nullCatalogMeansCurrent=true&serverTimezone=UTC}")
    String url;
    @Value("${spring.datasource.username:root}")
    String userName;
    @Value("${spring.datasource.password:root}")
    String password;

    /**
     * 初始化默认数据源
     */
    private void initDateSource(Environment environment) {
//        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(environment,
//                "spring.datasource.");
        Map<String, Object> dsMap = new HashMap<>();
        dsMap.put("type", type);
        dsMap.put("driver-class-name", driverClassName);
        dsMap.put("url", url);
        dsMap.put("username", userName);
        dsMap.put("password", password);
        //创建数据源。
        defaultDataSource = buildDataSource(dsMap);
        //设置附属数据源信息
//        dataBinderMore(defaultDataSource, environment);


    }


    /**
     * 创建DataSource
     */
    @SuppressWarnings("unchecked")
    public DataSource buildDataSource(Map<String, Object> dsMap) {
        try {
            Object type = dsMap.get("type");
            Class<? extends DataSource> dataSourceType;
            dataSourceType = (Class<? extends DataSource>) Class.forName((String) type);

            String driverClassName = dsMap.get("driver-class-name").toString();
            String url = dsMap.get("url").toString();
            String username = dsMap.get("username").toString();
            String password = dsMap.get("password").toString();

            DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName)
                    .url(url)
                    .username(username).password(password).type(dataSourceType);
            return factory.build();
        } catch (ClassNotFoundException e) {
            logger.error("DynamicDataSourceConfig.buildDataSource ClassNotFoundException error!", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 为DataSource绑定更多数据
     */
//    private void dataBinderMore(DataSource dataSource, Environment env) {
//        Binder binder = Binder.get(env);
////        RelaxedDataBinder dataBinder = new RelaxedDataBinder(dataSource);
//
////        binder.setConversionService(new DefaultConversionService());
////        binder.setIgnoreNestedProperties(false);//false
////        binder.setIgnoreInvalidFields(false);//false
////        binder.setIgnoreUnknownFields(true);//true
//        if (dataSourcePropertyValues == null) {
//            Map<String, Object> rpr = new RelaxedPropertyResolver(env, "spring.datasource.druid")
//                    .getSubProperties(".");
//            Map<String, Object> values = new HashMap<>(rpr);
//            // 排除已经设置的属性
//            values.remove("url");
//            values.remove("username");
//            values.remove("password");
//            dataSourcePropertyValues = new MutablePropertyValues(values);
//        }
//        binder.bind(dataSourcePropertyValues,PropertyValues.class);
//    }


}