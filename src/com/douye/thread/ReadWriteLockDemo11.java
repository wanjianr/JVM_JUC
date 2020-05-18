package com.douye.thread;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 2020年5月16日13:57:28
 *
 * 读写锁
 *
 * 独占锁：指该锁一次只能被一个线程所持有，ReentrantLock和Synchronized而言都是独占锁
 * 共享锁：指该锁可被多个线程所拥有
 *      ReadWriteLock的读锁是共享锁，写锁是独占锁
 *      共享锁可保证并发读的高效性，而读写、写读、写写的过程是互斥的
 */
class MyCatch {
    private volatile HashMap<String,Object> hashMap = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) throws InterruptedException {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始写-");
            TimeUnit.MICROSECONDS.sleep(300);
            hashMap.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入完成-");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key) throws InterruptedException {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始读-");
            TimeUnit.MICROSECONDS.sleep(300);
            hashMap.get(key);
            System.out.println(Thread.currentThread().getName()+"读取成功-");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}

public class ReadWriteLockDemo11 {
    public static void main(String[] args) {
        MyCatch myCatch = new MyCatch();
        for (int i = 0; i < 5; i++) {
            final int k = i;
            new Thread(()->{
                try {
                    myCatch.put(k+"",k+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++) {
            final int k = i;
            new Thread(()->{
                try {
                    myCatch.get(k+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
