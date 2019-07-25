package com.dubbo.customer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.api.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: WangKun
 * @Description:
 * @Date: Created in 2019/5/27 10:04 PM
 * @ProjectName: dubbo-parent
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

	@Reference(version = "${demo.service.version}")
	private DemoService demoService;

	@GetMapping(value = "/say")
	public String sayHello(@RequestParam("name") String name) {
		return demoService.sayHello(name);
	}
}
