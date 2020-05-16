package com.douye.interview;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 2020年5月11日21:14:15
 * 任务：验证volatile的可见性,和不具有原子性，
 * 第三条性质是：静止指令重排
 */
class MyData{
    int num = 0;
    public void addNum(){
        this.num = 20;
    }

    public void atomic() {
        this.num++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
}
public class VolatileDemo01 {
    public static void main(String[] args) {
        //seeVolatile();
        noAtomic();
    }

    private static void noAtomic() {
        MyData myData = new MyData();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 200; j++) {
                    myData.atomic();
                    myData.addAtomic();
                }
            },String.valueOf(i)).start();
        }
        // 等待上述线程都运算完
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println("int 10 * 20 = "+myData.num);
        System.out.println("atomic 10 * 20 = "+myData.atomicInteger);
    }

    // 验证volatile的可见性
    private static void seeVolatile() {
        MyData myData = new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName().toString()+"\trunning");
            try {
                TimeUnit.SECONDS.sleep(3); // 让该线程休眠三秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addNum(); // 修改主存中的值
            System.out.println(Thread.currentThread().getName().toString()+"\tupdate data!"+myData.num);
        },"A").start();

        while (myData.num == 0) {
            // 当没有线程修改主存中的值时，一直循环，否则跳出循环
            //System.out.println("main thread");
        }
        System.out.println(Thread.currentThread().getName().toString()+"\tmain thread is over!");
    }
}
