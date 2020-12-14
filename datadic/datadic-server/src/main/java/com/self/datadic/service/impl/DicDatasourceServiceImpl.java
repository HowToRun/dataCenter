package com.self.datadic.service.impl;

import com.self.basic.bean.vo.ResultContent;
import com.self.datadic.annotation.DynamicDB;
import com.self.datadic.entity.DicDatasource;
import com.self.datadic.mapper.DicDatasourceMapper;
import com.self.datadic.query.ExtTablebaseInfoQuery;
import com.self.datadic.service.IDicDatasourceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yihan.hu
 * @since 2020-12-12
 */
@Service
public class DicDatasourceServiceImpl extends ServiceImpl<DicDatasourceMapper, DicDatasource> implements IDicDatasourceService {

    @Resource
    DicDatasourceMapper dicDatasourceMapper;

    @Override
    public ResultContent datasourceTest(Long id) {
        ExtTablebaseInfoQuery query = new ExtTablebaseInfoQuery();
        query.setDataBaseId(String.valueOf(id));
        String name = dataSourceTestMethod(query, id);
        DicDatasource dicDatasource = selectById(id);

        return ResultContent.createSuccessResult(name);
    }

    @DynamicDB
    private String dataSourceTestMethod(ExtTablebaseInfoQuery query, Long id) {
        String name = dicDatasourceMapper.selectTest();
        System.out.println(name);
        System.out.println(id);
        return name;
    }
}
