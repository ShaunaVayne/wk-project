package com.cloud.service.api;

import com.cloud.service.bean.UserInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: WangKun
 * @Description:
 * @Date: Created in 2019/7/1 10:16 PM
 * @ProjectName: cloud-service
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("mock")
public class MockApi {

	@RequestMapping("test_01")
	public String test_01() {
		return "test_01";
	}

	@PostMapping("test_02")
	public UserInfo test_02(@RequestBody UserInfo userInfo) {
		userInfo.setCity("sss");
		return userInfo;
	}

	@PostMapping("test_03")
	public List<UserInfo> test_03(@RequestBody List<UserInfo> userInfoList) {
		List<UserInfo> ssss = userInfoList.stream().filter(e -> {
			e.setCity("ssss");
			return true;
		}).collect(Collectors.toList());
		return ssss;
	}


}
