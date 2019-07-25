package com.redis.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: WangKun
 * @Description:
 * @Date: Created in 2019/6/27 17:30
 * @ProjectName: redis-demo
 * @Version: 1.0.0
 */
@Component
@Slf4j
public class ScheduledService {

    /*@Scheduled(cron = "0/5 * * * * *")
    public void scheduled(){
        log.info("=====>>>>>使用cron  {}",System.currentTimeMillis());
    }*/
    /*@Scheduled(fixedRate = 5000)
    public void scheduled1() {
        log.info("=====>>>>>使用fixedRate{}", System.currentTimeMillis());
    }
    @Scheduled(fixedDelay = 5000)
    public void scheduled2() {
        log.info("=====>>>>>fixedDelay{}",System.currentTimeMillis());
    }*/
}
