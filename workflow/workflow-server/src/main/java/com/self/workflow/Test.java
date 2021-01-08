/**
 * Copyright (C), 2015 - 2020 , 北京和气聚力教育科技有限公司
 *
 * @fileName: Test
 * @author: huyih
 * @date: 2020/12/28 18:08
 * @description:
 * @history: <author>          <date>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.self.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class Test implements JavaDelegate {


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("test");
    }
}
