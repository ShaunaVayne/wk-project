package com.redis.demo.service.impl;

import com.redis.demo.service.TestService;
import com.redis.demo.util.async.TestAsync;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: WangKun
 * @Description:
 * @Date: Created in 2019/6/27 11:27 PM
 * @ProjectName: redis-demo
 * @Version: 1.0.0
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService {

	@Autowired
	private TestAsync testAsync;

	@Override
	public void testAsync() {
		log.info("testAsync--start");
		testAsync.test();
		log.info("testAsync--end");
	}
}
