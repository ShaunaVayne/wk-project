package com.netty.hello.telnet;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: WangKun
 * @Description:
 * @Date: Created in 2019/8/2 11:27
 * @ProjectName: wk-project
 * @Version: 1.0.0
 */
public class Test implements Runnable {

    public static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @Override
    public void run() {
        try {
            Thread.sleep(3000l);
            System.out.println("本身线程hhh");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class Inner {
        public static void main(String[] args) {
            executorService.execute(new Test());
            System.out.println("ok");
        }
    }
}
