package com.douye.interview;

import java.util.concurrent.ThreadPoolExecutor;

public class SingletonDemo02 {
    private static volatile SingletonDemo02 newInstance = null;

    private SingletonDemo02() {
        System.out.println(Thread.currentThread().getName()+"\t 创建SingletonDemo02对象");
    }

    /**
     * DCL (Double check lock)， 不一定安全，因为存在指令重排
     * 创建一个对象有以下三步
     *      1. memory = allocate() // 为对象分配内存空间
     *      2. instance（memory） // 初始化对象
     *      3. instance = memony // 设置instance指向刚分配的内存地址memory，此时instance！=null
     *  由于步骤2与3不存在数据依赖关系，重排前和重排后的执行结果不变，因此需要用volatile修饰newInstanceq
     */
    private static SingletonDemo02 getInstance() {
        if (newInstance == null) {
            synchronized (SingletonDemo02.class) {
                if (newInstance == null) {
                    newInstance = new SingletonDemo02();
                }
            }

        }
        return newInstance;
    }

    public static void main(String[] args) {
        // 单线程下使用单例模式不会出错
//        System.out.println(SingletonDemo02.getInstance() == SingletonDemo02.getInstance());
//        System.out.println(SingletonDemo02.getInstance() == SingletonDemo02.getInstance());
//        System.out.println(SingletonDemo02.getInstance() == SingletonDemo02.getInstance());

        for (int i = 0; i < 75; i++) {
            new Thread(()->{
                SingletonDemo02.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
