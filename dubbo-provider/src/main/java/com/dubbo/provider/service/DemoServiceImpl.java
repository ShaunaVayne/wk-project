package com.dubbo.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.api.DemoService;

/**
 * @Author: WangKun
 * @Description:
 * @Date: Created in 2019/5/27 9:52 PM
 * @ProjectName: dubbo-parent
 * @Version: 1.0.0
 */
@Service(version = "${demo.service.version}")
public class DemoServiceImpl implements DemoService {

	@Override
	public String sayHello(String name) {
		return "hello" + name + "i'm a provider";
	}
}
