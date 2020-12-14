package com.self.datadic.query;


import com.self.datadic.constant.DatadicConstant;

import java.io.Serializable;

public abstract class AbstractPageQuery implements Serializable {

    private Integer page = DatadicConstant.PAGE;

    private Integer limit = DatadicConstant.LIMIT;

    private String order;

    private String sidx;

    private String kw;

/*  @ApiModelProperty(value = "分页开始" ,hidden = true)
  private Integer offset;*/

    public String getKw() {
        return kw;
    }

    public void setKw(String kw) {
        this.kw = kw;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        if (limit != null && limit > DatadicConstant.LIMIT) {
            this.limit = DatadicConstant.LIMIT;
        } else {
            this.limit = limit;
        }
    }

    public String getOrder() {
        return SQLFilter.sqlInject(order);
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSidx() {
        return SQLFilter.sqlInject(sidx);
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public Integer getOffset() {
        return (page - 1) * limit;
    }
}
