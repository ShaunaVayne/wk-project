package com.cloud.feign.api;

import com.cloud.feign.bean.UserInfo;
import com.cloud.feign.service.MockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: WangKun
 * @Description:
 * @Date: Created in 2019/7/1 10:33 PM
 * @ProjectName: cloud-feign
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("feign")
@Slf4j
public class TestApi {

	@Autowired
	private MockService mockService;

	@RequestMapping("demo_01")
	public String demo_01() {
		String res = mockService.test_01();
		log.info("success-------");
		return res;
	}

	@PostMapping("demo_02")
	public UserInfo demo_02(@RequestBody UserInfo userInfo) {
		UserInfo s = mockService.test_02(userInfo);
		log.info("success-------:{}",s);
		return s;
	}

	@PostMapping("demo_03")
	public List<UserInfo> demo_03(@RequestBody List<UserInfo> userInfoList) {
		List<UserInfo> s = mockService.test_03(userInfoList);
		log.info("success-------:{}",s);
		return s;
	}
}
