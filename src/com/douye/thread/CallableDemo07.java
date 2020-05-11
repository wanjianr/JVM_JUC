package com.douye.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable实现了Runnable接口
 */
class MyThread implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("***Callable的call方法被调用了！");
        return 10;
    }
}
public class CallableDemo07 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(new MyThread());
        new Thread(task,"1").start();
        System.out.println(task.get());
    }
}
