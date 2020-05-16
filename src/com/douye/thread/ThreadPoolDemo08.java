package com.douye.thread;

import java.util.concurrent.*;

/**
 * 创建一个线程池所需的七个参数
 * 1. corePoolSize：线程池中的常驻核心线程数
 * 2、maximumPoolSize：线程池中能够容纳同时执行的最大线程数，此值必须大于等于1
 * 3、keepAliveTime：多余的空闲线程的存活时间当前池中线程数量超过corePoolSize时，当空闲时间
 * 达到keepAliveTime时，多余线程会被销毁直到只剩下corePoolSize个线程为止
 * 4、unit：keepAliveTime的单位
 * 5、workQueue：任务队列，被提交但尚未被执行的任务
 * 6、threadFactory：表示生成线程池中工作线程的线程工厂，用于创建线程，一般默认的即可
 * 7、handler：拒绝策略，表示当队列满了，并且工作线程大于等于线程池的最大线程数
 * （maximumPoolSize）时如何来拒绝请求执行的runnable的策略
 * - AbortPolicy(默认)：直接抛出RejectedExecutionException异常阻止系统正常运行
 * - CallerRunsPolicy：“调用者运行”一种调节机制，该策略既不会抛弃任务，也不
 * 会抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量。
 * - DiscardOldestPolicy：抛弃队列中等待最久的任务，然后把当前任务加人队列中
 * 尝试再次提交当前任务。
 * - DiscardPolicy：该策略默默地丢弃无法处理的任务，不予任何处理也不抛出异常。
 * 如果允许任务丢失，这是最好的一种策略。
 */

public class ThreadPoolDemo08 {
    public static void main(String[] args) {
        myThreadPool();
        //defaultThread();
    }

    private static void myThreadPool() {
        System.out.println("CPU核数："+Runtime.getRuntime().availableProcessors());
        ExecutorService thread = new ThreadPoolExecutor(2,
                5, //maximumPoolSize = cpu核数+1
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        try {
            for (int i = 0; i < 10; i++) {
                //TimeUnit.SECONDS.sleep(1);
                thread.execute(() -> {
                    System.out.println(Thread.currentThread().getName().toString() + "\t办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            thread.shutdown();
        }
    }

    private static void defaultThread() {
        ExecutorService myThreadPool = Executors.newCachedThreadPool(); // 一池n线程，会根据任务数量，动态增加线程数
        ExecutorService myThreadPool2 = Executors.newFixedThreadPool(6);  // 指定池中的线程数
        ExecutorService myThreadPool3 = Executors.newSingleThreadExecutor(); // 线程池中只有一个线程
        try {
            for (int i = 0; i < 10; i++) {
                //TimeUnit.SECONDS.sleep(1);
                myThreadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName().toString() + "\t办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myThreadPool.shutdown();
        }
    }
}
