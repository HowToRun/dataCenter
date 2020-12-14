package com.self.datadic.query;


public class TablebaseInfoQuery extends AbstractPageQuery {

  //连接信息Id
  private String dataBaseId;
  private String jdbcUrl;
  private String username;
  private String password;
  private Integer type;
  private String name;

  public String getDataBaseId() {
    return dataBaseId;
  }

  public void setDataBaseId(String dataBaseId) {
    this.dataBaseId = dataBaseId;
  }

  public String getJdbcUrl() {
    return jdbcUrl;
  }

  public void setJdbcUrl(String jdbcUrl) {
    this.jdbcUrl = jdbcUrl;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
