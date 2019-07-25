package com.dubbo.customer.services;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.api.TestService;

/**
 * @Author: WangKun
 * @Description:
 * @Date: Created in 2019/5/28 11:55
 * @ProjectName: dubbo-parent
 * @Version: 1.0.0
 */
@Service(version = "${demo.service.version}")
public class TestServiceImpl implements TestService {

    @Override
    public String test(String name) {
        return "test:1515"+ name;
    }
}
