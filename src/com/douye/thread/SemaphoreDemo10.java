package com.douye.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 2020年5月16日10:45:49
 *
 * Semaphore包括两种操作
 *      acquire（获取）：当一个线程调用该方法时，他要么成功获取信号量（信号量减一）
 *          要么一直等下去，直到线程释放信号量，或超时i
 *      release（释放）：实际上会将信号量的值加一，然后唤醒等待线程
 *
 * 信号量主要有两个目的：
 *      用于多个共享资源的互斥使用
 *      用于并发线程数的控制
 */

public class SemaphoreDemo10 {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5); // 5个车位

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    TimeUnit.SECONDS.sleep(3); // 占用车位3秒
                    System.out.println(Thread.currentThread().getName()+"离开");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
