package com.self.datadic.controller;


import com.self.basic.bean.vo.ResultContent;
import com.self.datadic.service.IDicDatasourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yihan.hu
 * @since 2020-12-12
 */
@Controller
@RequestMapping("/dicDatasource")
public class DicDatasourceController {

    @Autowired
    IDicDatasourceService dicDatasourceService;

    @RequestMapping(value = "/datasourceTest",method = RequestMethod.GET)
    public ResultContent datasourceTest(@RequestParam Long id) throws IOException {
        return dicDatasourceService.datasourceTest(id);

    }
}
