package com.self.datadic.query;

import com.self.datadic.constant.DatadicConstant;

import java.util.ArrayList;
import java.util.List;

public class ExtTablebaseInfoQuery extends TablebaseInfoQuery {

  private static final long serialVersionUID = 1L;


  //数据库名
  private String name;
  //数据库实例
  private String instance;

  //表Table
  private String tableName;

  //表TableId
  private String tableId;

  //字段串
  private String fields;

  private String schema;

  private List<String> tableNameList = new ArrayList<>();


  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }


  //默认显示多少条记录
  private Integer defaultLimit = DatadicConstant.DEFAULT_LIMIT;

  public void setInstance(String instance) {
    this.instance = instance;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getTableId() {
    return tableId;
  }

  public void setTableId(String tableId) {
    this.tableId = tableId;
  }

  public String getInstance() {
    return instance;
  }

  public String getFields() {
    return fields;
  }

  public void setFields(String fields) {
    this.fields = fields;
  }

  public String getSchema() {
    return schema;
  }

  public void setSchema(String schema) {
    this.schema = schema;
  }



  public List<String> getTableNameList() {
    return tableNameList;
  }

  public void setTableNameList(List<String> tableNameList) {
    this.tableNameList = tableNameList;
  }
}
