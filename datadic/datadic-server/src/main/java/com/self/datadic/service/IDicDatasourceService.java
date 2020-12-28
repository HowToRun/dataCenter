package com.self.datadic.service;

import com.self.basic.bean.vo.ResultContent;
import com.self.datadic.annotation.DynamicDB;
import com.self.datadic.entity.DicDatasource;
import com.baomidou.mybatisplus.service.IService;
import com.self.datadic.query.ExtTablebaseInfoQuery;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yihan.hu
 * @since 2020-12-12
 */
public interface IDicDatasourceService extends IService<DicDatasource> {

    ResultContent datasourceTest(Long id);

    @DynamicDB
    @Transactional
    ResultContent dataSourceTestMethod(ExtTablebaseInfoQuery query, Long id);
}
