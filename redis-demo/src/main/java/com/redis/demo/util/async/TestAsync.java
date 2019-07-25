package com.redis.demo.util.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

/**
 * @Author: WangKun
 * @Description:
 * @Date: Created in 2019/7/10 15:25
 * @ProjectName: redis-demo
 * @Version: 1.0.0
 */
@Component
@Slf4j
public class TestAsync {

    @Async
    public void test() {
        log.info("test方法开始:线程名:{},时间:{}",Thread.currentThread().getName(),System.currentTimeMillis());
        IntStream.range(0,5).forEach(e -> {
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        log.info("test方法结束:线程名:{},时间:{}",Thread.currentThread().getName(),System.currentTimeMillis());
    }
}
