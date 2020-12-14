package com.self.datadic.dynamic;

public class DataSourceContext {


  private static ThreadLocal<DataSourceBean> threadLocal = new ThreadLocal<DataSourceBean>() {
    @Override
    protected DataSourceBean initialValue() {
      return null;
    }
  };

  public static DataSourceBean getDataSource() {
    return threadLocal.get();
  }

  //使用该方法设置数据源
  public static void setDataSource(DataSourceBean dataSourceBean) {
    threadLocal.set(dataSourceBean);
  }

  //使用该方法清除数据源，清除后将使用默认数据源
  public static void clearDataSource() {
    threadLocal.remove();
  }
}
