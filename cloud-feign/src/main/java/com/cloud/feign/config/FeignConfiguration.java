package com.cloud.feign.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: WangKun
 * @Description:
 * @Date: Created in 2019/7/1 10:57 PM
 * @ProjectName: cloud-feign
 * @Version: 1.0.0
 */

@Configuration
public class FeignConfiguration implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String authorization = request.getHeader("Authorization");

		template.header("Authorization", authorization);
	}
}
