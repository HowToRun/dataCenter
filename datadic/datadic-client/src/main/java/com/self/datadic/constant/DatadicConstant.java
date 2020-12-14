/**
 * Copyright (C), 2015 - 2020 , 北京和气聚力教育科技有限公司
 *
 * @fileName: DatadicConstant
 * @author: huyih
 * @date: 2020/12/12 18:28
 * @description:
 * @history: <author>          <date>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.self.datadic.constant;

public class DatadicConstant {


    public static final Integer LIMIT = 10000;

    public static final Integer DEFAULT_LIMIT = 10;

    public static final Integer PAGE = 1;

    /**
     * 数据库类型
     */
    public static final int ORACLE = 2;
    public static final int SQLSERVER = 5;
    public static final int MYSQL = 1;
    public static final int DB2 = 6;
    public static final int HIVE = 3;
    public static final int HBASE = 7;
    public static final int DM = 13;

    public static final String DRIVERCLASS_MYSQL = "com.mysql.cj.jdbc.Driver";
    /*public static final String DRIVERCLASS_MYSQL = "com.mysql.jdbc.Driver";*/
    public static final String DRIVERCLASS_ORACLE = "oracle.jdbc.driver.OracleDriver";
    public static final String DRIVERCLASS_SQLSERVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String DRIVERCLASS_DB2 = "com.ibm.db2.jcc.DB2Driver";
    public static final String DRIVERCLASS_POSTGRESQL = "org.postgresql.Driver";
    public static final String DRIVERCLASS_SYBASE = "com.sybase.jdbc3.jdbc.SybDriver";
    public static final String DRIVERCLASS_DM = "dm.jdbc.driver.DmDriver";
    public static final String DRIVERCLASS_KINGBASEES = "com.kingbase.Driver";
    //public static final String DRIVERCLASS_ACCESS = "net.ucanaccess.jdbc.UcanaccessDriver";
    public static final String DRIVERCLASS_ACCESS = "org.objectweb.rmijdbc.Driver";
    public static final String DRIVERCLASS_HIVE = "org.apache.hive.jdbc.HiveDriver";

    public static final String VALIDATIONQUERY_MYSQL = "select 1";
    public static final String VALIDATIONQUERY_ORACLE = "select 1 from dual";
    public static final String VALIDATIONQUERY_SQLSERVER = "select 1";
    public static final String VALIDATIONQUERY_DB2 = "select 1 from sysibm.sysdummy1";
    public static final String VALIDATIONQUERY_POSTGRESQL = "select 1";
    public static final String VALIDATIONQUERY_SYBASE = "select 1";
    public static final String VALIDATIONQUERY_DM = "select 1 from dual";
    public static final String VALIDATIONQUERY_KINGBASEES = "select 1 from dual";
    public static final String VALIDATIONQUERY_ACCESS = "select 1";

    public static final String URL_MYSQL_FORMATTER = "jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=Hongkong&useSSL=false";
    public static final String URL_ORACLE_FORMATTER = "jdbc:oracle:thin:@%s:%s/%s";
    public static final String URL_SQLSERVER_FORMATTER = "jdbc:sqlserver://%s:%s;DatabaseName=%s";
    public static final String URL_DB2_FORMATTER = "jdbc:db2://%s:%s/%s";
    public static final String URL_HIVE_FORMATTER = "jdbc:hive2://%s/%s;serviceDiscoveryMode=zooKeeper;zooKeeperNamespace=hiveserver2";
    public static final String URL_SYBASE_FORMATTER = "jdbc:sybase:Tds:%s:%s/%s";
    public static final String URL_DM_FORMATTER = "jdbc:dm://%s:%s/%s";
    public static final String URL_KINGBASEES_FORMATTER = "jdbc:kingbase://%s:%s/%s";
    public static final String URL_KINGBASEES_ACCESS = "jdbc:ucanaccess://%s";
}
