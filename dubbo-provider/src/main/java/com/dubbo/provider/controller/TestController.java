package com.dubbo.provider.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.api.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: WangKun
 * @Description:
 * @Date: Created in 2019/5/28 11:57
 * @ProjectName: dubbo-parent
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Reference(version = "${demo.service.version}")
    private TestService testService;

    @GetMapping(value = "/say")
    public String say(@RequestParam("name") String name) {
        return testService.test(name);
    }
}
