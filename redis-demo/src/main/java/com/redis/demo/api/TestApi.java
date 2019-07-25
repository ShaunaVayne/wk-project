package com.redis.demo.api;

import com.redis.demo.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: WangKun
 * @Description:
 * @Date: Created in 2019/6/27 11:23 PM
 * @ProjectName: redis-demo
 * @Version: 1.0.0
 */
@RestController
@Slf4j
public class TestApi {

	@Autowired
	private TestService testService;

	@RequestMapping(value = "/async",method = RequestMethod.GET)
	public String async() {
		log.info("api方法开始:线程名:{},时间:{}",Thread.currentThread().getName(),System.currentTimeMillis());
		testService.testAsync();
		log.info("api方法结束:线程名:{},时间:{}",Thread.currentThread().getName(),System.currentTimeMillis());
		return "success";
	}
}
