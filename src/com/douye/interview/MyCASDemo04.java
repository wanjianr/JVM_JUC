package com.douye.interview;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 2020年5月16日16:10:02
 *
 * 自旋锁（spinlock）：指尝试获取锁的线程不会立即阻塞，而是采用循环的方方式去尝试
 * 获取锁，因此可以减少线程上下文切换的消耗，但是循环会消耗cpu
 */
public class MyCASDemo04 {

    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void mylock() throws InterruptedException {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"***lock");
        while (! atomicReference.compareAndSet(null,thread)) {
            // 锁代码
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+"等待获取锁...");
        }
    }

    public void myunlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"--unlock--");
    }

    public static void main(String[] args) throws InterruptedException {
        MyCASDemo04 myCASDemo04 = new MyCASDemo04();
        new Thread(()->{
            try {
                myCASDemo04.mylock();
                TimeUnit.SECONDS.sleep(5);
                myCASDemo04.myunlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(()->{
            try {
                myCASDemo04.mylock();
                myCASDemo04.myunlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
    }
}
