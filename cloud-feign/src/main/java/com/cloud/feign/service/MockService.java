package com.cloud.feign.service;

import com.cloud.feign.bean.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: WangKun
 * @Description:
 * @Date: Created in 2019/7/1 10:29 PM
 * @ProjectName: cloud-feign
 * @Version: 1.0.0
 */
@Component
@FeignClient(value = "cloud-service")
public interface MockService {

	@RequestMapping("/mock/test_01")
	String test_01();

	@PostMapping("/mock/test_02")
	UserInfo test_02(UserInfo userInfo);

	@PostMapping("/mock/test_03")
	List<UserInfo> test_03(List<UserInfo> userInfoList);
}
