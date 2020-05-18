package com.douye.interview;

import java.util.concurrent.TimeUnit;

/**
 * 2020年5月17日16:35:57
 *      排查死锁：
 *          jps -l // 查看java程序进程号
 *          jstack 进程号 // 查看指定进程的信息
 *
 */

class MyResource implements Runnable {

    private String lockA;
    private String lockB;

    public MyResource(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName()+"\t拥有"+lockA+"\t等待获取"+lockB);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName()+"\t拥有"+lockB+"\t等待获取"+lockA);
            }
        }
    }
}
public class DeadLockDemo05 {
    public static void main(String[] args) {
        new Thread(new MyResource("lockA","lockB"),"AAA").start();
        new Thread(new MyResource("lockB","lockA"),"BBB").start();
    }
}
