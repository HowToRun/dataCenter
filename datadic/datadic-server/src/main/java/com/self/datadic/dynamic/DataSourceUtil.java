package com.self.datadic.dynamic;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.self.datadic.constant.DatadicConstant;
import com.self.datadic.entity.DicDatasource;
import com.self.datadic.mapper.DicDatasourceMapper;
import com.self.datadic.query.ExtTablebaseInfoQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Component
public class DataSourceUtil {
  private static final Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);
  @Resource
  private DicDatasourceMapper dicDatasourceMapper;

  public static DataSourceUtil dbSourceUtil;

  @PostConstruct
  public void init() {
    dbSourceUtil = this;
  }

  /**
   * 根据id查询创建数据源信息
   */
  public static DataSourceBean getDataSourceById(ExtTablebaseInfoQuery query) {

    //获取数据源信息
    Map<String,Object> param = new HashMap<String,Object>();
    String dataBaseId = query.getDataBaseId();
    param.put("id", dataBaseId);

    System.out.println("====================要切换的数据源id====================================" + dataBaseId);
    List<DicDatasource> result  = dbSourceUtil.dicDatasourceMapper.selectByMap(param);
    if(1!=result.size()){
          //必须返回一个结果
        if(result.size()==0){
            System.out.println("====================数据源切换时查找不到数据源===================================="+result.size());
            throw  new RuntimeException("数据源切换时查找不到数据源");
        }else{
            System.out.println("=====================数据源切换时查找到的数据源不唯一===================================="+result.size());
            throw  new RuntimeException("数据源切换时查找到的数据源不唯一");
        }
    }
    DicDatasource dbInfo = result.get(0);
    Integer port = dbInfo.getPort();
    //todo hive jdbc通过zk连接
//    if (DatadicConstant.HIVE==dbInfo.getType()) {
//      HiveConfigEnity hiveConfigEnity = new ReadXMLByDom4j().getHiveConfig(dbInfo.getConfigXml());
//      dbInfo.setHost(hiveConfigEnity.getZkAddress());
//      /**
//       * hive jdbc 不需要端口
//       */
//      if(null==dbInfo.getPort()){
//        port = -1;
//      }
//      //数据库名是从前端传来
//      dbInfo.setDataBase(query.getName());
//    }

    try{

      DataSourceBean bean = new DataSourceBean(
              dbInfo.getDataBase(),getUrl(dbInfo.getHost(),-1 ==port ?"":port.toString(),dbInfo.getDataBase(),dbInfo.getType().intValue()),
              dbInfo.getUserName(), dbInfo.getPassword()
              , dbInfo.getType().intValue());
      return bean;
    }catch (Exception e){
      logger.error("数据源转换异常，可能是有值为null");
      e.printStackTrace();
      throw  new RuntimeException("获取数据源相关信息失败！！！！");

    }

  }

  /**
   * 根据数据源信息创建数据源信息
   */
  /*public static DataSourceBean getDataSourceByName(ResourceInfoConfigEntity query) {
    //根据名称查询数据库主机信息
    ResourceInfoConfigEntity dbInfo = dbSourceUtil.resourceInfoConfigEntityMapper.queryInfoByCondition(query);
    DataSourceBean bean = new DataSourceBean(
        dbInfo.getName(), dbInfo.getJdbcUrl(),
        dbInfo.getUsername(), dbInfo.getPassword()
        , dbInfo.getType());
    //重新设置用户名称
    query.setUsername(dbInfo.getUsername());
    return bean;
    return new DataSourceBean("","","","",1);
  }*/

  /**
   * 根据数据源信息创建数据源信息
   */
  /*public static DataSourceBean getDataSourceByObject(ExtTablebaseInfoQuery query) {

    DataSourceBean bean = new DataSourceBean(
        query.getName(), query.getJdbcUrl(),
        query.getUsername(), query.getPassword()
        , query.getType());

    return bean;
  }
*/
  //测试连接时，有可能没保存，所以在提供一个方法，不去取数据库信息，使用前端传入的信息进行测试连接
  public static boolean testConnection(DicDatasource dbInfo) {
    DataSource dataSource = null;

    Properties p = new Properties();
    p.put("initialSize", "1");
    p.put("minIdle", "1");
    p.put("maxActive", "20");
    p.put("maxWait", "60000");
    p.put("timeBetweenEvictionRunsMillis", "60000");
    p.put("minEvictableIdleTimeMillis", "300000");

    p.put("testWhileIdle", "true");
    p.put("testOnBorrow", "false");
    p.put("testOnReturn", "false");
    p.put("poolPreparedStatements", "true");
    p.put("maxPoolPreparedStatementPerConnectionSize", "20");
    p.put("filters", "stat");

    p.put("username", dbInfo.getUserName());
    p.put("password", dbInfo.getPassword());


    p.put("url",getUrl(dbInfo.getHost(),dbInfo.getPort().toString(),dbInfo.getDataBase(),dbInfo.getType().intValue()));
//        switch (dbInfo.getType()) {
//            case DBType.ORACLE://Oracle->jdbc:oracle:thin:@192.168.61.73:11521/PERSONCOUNT
//                p.put("url",
//                        "jdbc:oracle:thin:@" + dbInfo.getIp() + ":" + dbInfo.getPort() + "/" + dbInfo
//                                .getInstance()
//                );
//                p.put("validationQuery", "SELECT 1 ");
//                break;
//            case DBType.SQLSERVER://SQL SERVER->jdbc:sqlserver://localhost:1433;DatabaseName=student_course;
//                p.put("url",
//                        "jdbc:sqlserver://" + dbInfo.getIp() + ":" + dbInfo.getPort() + ";DatabaseName="
//                                + dbInfo.getInstance()
//                                + "?useUnicode=true&characterEncoding=utf8");
//                p.put("validationQuery", "SELECT 1 FROM dual");
//                break;
//            case DBType.MYSQL://MySQL ->jdbc:mysql://localhost:3306/dynamicdb
//                p.put("url",
//                        "jdbc:mysql://" + dbInfo.getIp() + ":" + dbInfo.getPort() + "/" + dbInfo.getInstance()
//                                + "?useUnicode=true&characterEncoding=utf8");
//                p.put("validationQuery", "SELECT 1 FROM dual");
//                break;
//            case DBType.DB2://DB2 ->jdbc:db2://localhost:50000/toolsdb
//                p.put("url",
//                        "jdbc:db2://" + dbInfo.getIp() + ":" + dbInfo.getPort() + "/" + dbInfo.getInstance()
//                                + "?useUnicode=true&characterEncoding=utf8");
//                p.put("validationQuery", "SELECT 1 FROM dual");
//                break;
//            default://MySQL - >jdbc:mysql://localhost:3306/dynamicdb
//                p.put("url",
//                        "jdbc:mysql://" + dbInfo.getIp() + ":" + dbInfo.getPort() + "/" + dbInfo.getInstance()
//                                + "?useUnicode=true&characterEncoding=utf8");
//                p.put("validationQuery", "SELECT 1 FROM dual");
//                break;
//
//        }
    try {
      dataSource = DruidDataSourceFactory.createDataSource(p);
      Connection connection = dataSource.getConnection();
      if (connection != null) {
        connection.close();
        return true;
      }
    } catch (Exception e) {
    }

    return false;
  }


  public static String getUrl(String databaseIP,String databasePort,String databaseName,int type) {
    if (DatadicConstant.ORACLE == type) {
      return String
              .format(DatadicConstant.URL_ORACLE_FORMATTER, databaseIP, databasePort, databaseName);
    } else if (DatadicConstant.SQLSERVER == type) {
      return String
              .format(DatadicConstant.URL_SQLSERVER_FORMATTER, databaseIP, databasePort, databaseName);
    } else if (DatadicConstant.MYSQL == type) {
      return String
              .format(DatadicConstant.URL_MYSQL_FORMATTER, databaseIP, databasePort, databaseName);
    } else if (DatadicConstant.DB2 == type) {
      return String
              .format(DatadicConstant.URL_DB2_FORMATTER, databaseIP, databasePort, databaseName);
    } else if (DatadicConstant.HIVE == type) {
      return String
              .format(DatadicConstant.URL_HIVE_FORMATTER, databaseIP, databaseName);
    }else if (DatadicConstant.DM == type) {
      return String
              .format(DatadicConstant.URL_DM_FORMATTER, databaseIP,databasePort,databaseName);
    }else {
      return String
              .format(DatadicConstant.URL_MYSQL_FORMATTER, databaseIP, databasePort, databaseName);
    }
  }

  /**
   * 根据(view_uoion_resource_info_config)id查询数据源信息
   */
//  public static ResourceInfoConfigEntity getResourceInfoById(String id) {
//
//    //获取数据源信息
//    Map<String,Object> param = new HashMap<String,Object>();
//    param.put("vid",id);
//    List<ResourceInfoConfigEntity> result  = dbSourceUtil.resourceInfoConfigEntityMapper.queryList(param);
//    if(1!=result.size()){
//        if(result.size()==0){
//            System.out.println("====================查找不到数据源===================================="+result.size());
//            throw  new RRException("查找不到数据源");
//        }else{
//            //必须返回一个结果
//            System.out.println("====================查找到的数据源不唯一===================================="+result.size());
//            throw  new RRException("查找到的数据源不唯一");
//        }
//    }
//    ResourceInfoConfigEntity dbInfo = result.get(0);
//
//   // ResourceInfoConfigEntity dbInfo = dbSourceUtil.resourceInfoConfigEntityMapper.queryObject(id);
//
//    return dbInfo;
//  }
}
