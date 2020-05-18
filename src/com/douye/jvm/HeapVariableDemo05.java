package com.douye.jvm;

import java.util.Random;

/**
 * 2020年5月17日10:48:46
 *  -Xms: 设置初始堆内存分配大小，默认为物理内存的1/64
 *  -Xmx: 最大分配内存，默认为物理内存的1/4
 *  -XX:+PrintGCDetails: 输出详细的GC日志
 */
public class HeapVariableDemo05 {
    public static void main(String[] args) {
        System.out.println("物理机核数："+Runtime.getRuntime().availableProcessors());
        // 获取虚拟机试图使用的最大内存
        long maxMemory = Runtime.getRuntime().maxMemory();
        // 获取虚拟机的内存总量（初始内存）
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("MAX_MEMORY = " + maxMemory + "（字节）、" + (maxMemory / (double)1024 / 1024) + "MB");
        System.out.println("TOTAL_MEMORY = " + totalMemory + "（字节）、" + (totalMemory / (double)1024 / 1024) + "MB");

        System.out.println();
        //byte[] bytes = new byte[1024*1024*10]; // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        String str = "douye";
        while (true) {
            str += str + new Random().nextInt(99999999) + new Random().nextInt(88888888);
        }

    }
}
