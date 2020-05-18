package com.douye.thread;

import com.douye.enumerate.CountryEnum;

import java.util.concurrent.CountDownLatch;

/**
 * 2020年5月16日10:17:58
 * 确保子线程全部执行完成
 *
 * CountDownLatch主要有两种方法，当一个或多个线程调用await方法时，这些线程会堵塞
 * 其他线程调用countDown方法时会将计数器减一（调用此方法的线程不会堵塞）
 * 当计数器值为0时，因await方法阻塞的线程会被唤醒，继续执行
 */
public class CountDownLatchDemo08 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" 离开教室");
                countDownLatch.countDown();
            }, CountryEnum.forEach_CountryEnum(i+1).getRetMessage()).start();
        }
        countDownLatch.await();
        System.out.println("班长关闭教室");
    }
}
