package com.douye.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Callable实现了Runnable接口
 * 与Runnable的不同
 *      1. 有返回值
 *      2. 有异常处理
 *      3. 落地函数不同call()
 */
class MyThread implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        System.out.println(Thread.currentThread().getName()+"***Callable的call方法被调用了！");
        return 10;
    }
}
public class CallableDemo07 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(new MyThread());
        new Thread(task,"A").start();
        new Thread(task,"B").start();  // 多个线程调用同一个task时，只执行其中一个，并将结果缓存供其它线程直接获得结果
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.currentThread().getName()+"****");
        System.out.println(task.get());
    }
}
