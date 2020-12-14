package com.self.datadic.mapper;

import com.self.datadic.entity.DicDatasource;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yihan.hu
 * @since 2020-12-12
 */
@Mapper
public interface DicDatasourceMapper extends BaseMapper<DicDatasource> {

    @Select("SELECT name from test1table limit1")
    String selectTest();

}
