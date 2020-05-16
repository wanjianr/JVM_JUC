package com.douye.interview;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 2020年5月13日20:20:44
 * 可重入锁（递归锁）synchronized/ReentrantLock
 *      同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码
 *      在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁
 *
 *      即，线程可以进入任何一个它已拥有的锁所同步着的代码块
 */
class Phone implements Runnable {

    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getName()+"\t发送即时信息");
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName()+"\t*******发送Email");
    }

    @Override
    public void run() {
        get();
    }
    private void get() {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\tget()");
            set();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    private void set() {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t**********set()");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ReentrantLockDemo04 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->phone.sendSMS(),"synchronized线程1").start();
        new Thread(()->phone.sendSMS(),"synchronized线程2").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        Thread thread1 = new Thread(phone, "ReentrantLock线程1");
        Thread thread2 = new Thread(phone, "ReentrantLock线程2");
        thread1.start();
        thread2.start();
    }
}
