package com.redis.demo;

import com.alibaba.fastjson.JSON;
import com.redis.demo.service.TestService;
import com.redis.demo.util.RedisTemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisDemoApplicationTests {

    @Autowired
    private RedisTemplateUtil redisTemplateUtil;

    @Autowired
    private TestService testService;

    private static final String order_obj = "group::order::obj";

    @Test
    public void test1() {
        redisTemplateUtil.set(order_obj,"sss",2000l);
        log.info("{}:返回结果:{}", "", JSON.toJSONString("成功"));
    }

    @Test
    public void test2() {
        testService.opt(18l);
        log.info("{}:返回结果:{}", "", JSON.toJSONString("成功"));

    }

}
