package com.self.datadic.dynamic;

import com.self.datadic.constant.DatadicConstant;

public class DataSourceBean {


  private String beanName;
  private String url;
  private String username;
  private String password;
  private String validationQuery = null;
  private final boolean testOnBorrow = true;
  private String driverClassName = null;
  private Integer type;

  public DataSourceBean() {
  }

  public DataSourceBean(String beanName, String url, String username, String password,
                        Integer type) {
    this.beanName = beanName;
    this.url = url;
    this.username = username;
    this.password = password;
    this.type = type;
    this.validationQuery = this.getValidationQuery();
    this.driverClassName = this.getDriverClassName();
  }

  public void setBeanName(String beanName) {
    this.beanName = beanName;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getBeanName() {
    return beanName;
  }

  public Integer getType() {
    return type;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }


  public final String getValidationQuery() {
    if (DatadicConstant.ORACLE == this.type) {
      return DatadicConstant.VALIDATIONQUERY_ORACLE;
    } else if (DatadicConstant.SQLSERVER == this.type) {
      return DatadicConstant.VALIDATIONQUERY_SQLSERVER;
    } else if (DatadicConstant.MYSQL == this.type) {
      return DatadicConstant.VALIDATIONQUERY_MYSQL;
    } else if (DatadicConstant.DB2 == this.type) {
      return DatadicConstant.VALIDATIONQUERY_DB2;
    } else if (DatadicConstant.DM == this.type) {
      return DatadicConstant.VALIDATIONQUERY_DM;
    }else {
      return DatadicConstant.VALIDATIONQUERY_MYSQL;
    }
  }

  public boolean isTestOnBorrow() {
    return testOnBorrow;
  }

  public String getDriverClassName() {
    if (DatadicConstant.ORACLE == this.type) {
      return DatadicConstant.DRIVERCLASS_ORACLE;
    } else if (DatadicConstant.SQLSERVER == this.type) {
      return DatadicConstant.DRIVERCLASS_SQLSERVER;
    } else if (DatadicConstant.MYSQL == this.type) {
      return DatadicConstant.DRIVERCLASS_MYSQL;
    } else if (DatadicConstant.DB2 == this.type) {
      return DatadicConstant.DRIVERCLASS_DB2;
    } else if (DatadicConstant.HIVE == this.type) {
      return DatadicConstant.DRIVERCLASS_HIVE;
    }else if (DatadicConstant.DM == this.type) {
      return DatadicConstant.DRIVERCLASS_DM;
    }
    else {
      return DatadicConstant.DRIVERCLASS_MYSQL;
    }
  }

  public String getUrl() {
    return this.url;
  }


  @Override
  public String toString() {
    return "DataSourceBean{" +
        " driverClassName='" + driverClassName + '\'' +
        ", url='" + url + '\'' +
        ", username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", validationQuery='" + validationQuery + '\'' +
        ", testOnBorrow=" + testOnBorrow +
        '}';
  }
}
