package com.douye.thread;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 要求：多个线程按顺序调用，A--B--C
 */
class PrintApp {
    private int flag = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    public void printA() {
        lock.lock();
        try {
            // 判断
            while (flag != 1) {
                condition1.await();
            }
            // 业务
            for (int i = 0; i < 2; i++) {
                System.out.println(Thread.currentThread().getName().toString()+"\t"+"A");
            }
            // 更新标志位，通知
            flag = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printB() {
        lock.lock();
        try {
            // 判断
            while (flag != 2) {
                condition2.await();
            }
            // 业务
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName().toString()+"\t"+"B");
            }
            // 更新标志位，通知
            flag = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printC() {
        lock.lock();
        try {
            // 判断
            while (flag != 3) {
                condition3.await();
            }
            // 业务
            for (int i = 0; i < 4; i++) {
                System.out.println(Thread.currentThread().getName().toString()+"\t"+"C");
            }
            // 更新标志位，通知
            flag = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ConditionDemo06 {
    public static void main(String[] args) {
        PrintApp printApp = new PrintApp();
        new Thread(()->{
            for (int i = 0; i < 3; i++) {
                printApp.printC();
            }},"thread 3:").start();
        new Thread(()->{
            for (int i = 0; i < 3; i++) {
                printApp.printA();
            }},"thread 1:").start();
        new Thread(()->{
            for (int i = 0; i < 3; i++) {
                printApp.printB();
            }},"thread 2:").start();
    }
}
