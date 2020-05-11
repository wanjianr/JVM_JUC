package com.douye.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 2020年5月10日18:35:43
 * 1. 高类聚低耦合下，线程操作资源类
 * 2. 判断->干活->通知
 * 3. 防止虚假唤醒(判断用while，不能用if)
 */
class AirCondition {
    private int tempture = 0;


    // -------新版本的写法----------
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void add() throws InterruptedException {
        lock.lock();
        try {
            // 判断
            while (tempture != 0) {
                condition.await();
            }
            // 操作
            tempture++;
            System.out.println(Thread.currentThread().getName().toString()+"\t"+tempture);

            // 通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void subtract() throws InterruptedException {
        lock.lock();
        try {
            // 判断
            while (tempture == 0) {
                condition.await();
            }
            // 操作
            tempture--;
            System.out.println(Thread.currentThread().getName().toString()+"\t"+tempture);

            // 通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
    // -------老版本的写法----------
//    public synchronized void add() throws InterruptedException {
//        // 判断
//        while (tempture != 0) {
//            this.wait();
//        }
//        // 操作
//        tempture++;
//        System.out.println(Thread.currentThread().getName().toString()+"\t"+tempture);
//
//        // 通知
//        this.notifyAll();
//    }
//
//    public synchronized void subtract() throws InterruptedException {
//        // 判断
//        while (tempture == 0) {
//            this.wait();
//        }
//        // 操作
//        tempture--;
//        System.out.println(Thread.currentThread().getName().toString()+"\t"+tempture);
//
//        // 通知
//        this.notifyAll();
//    }
}

public class ProdConsumerDemo05 {
    public static void main(String[] args) {
        AirCondition airCondition = new AirCondition();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    airCondition.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    airCondition.subtract();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    airCondition.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    airCondition.subtract();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}
