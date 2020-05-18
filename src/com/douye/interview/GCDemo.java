package com.douye.interview;

/**
 * 1. DefNew + Tenured
 *      -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC
 *
 * 2. ParNew + Tenured
 *      -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC
 *
 * 3. PSYoungGen + ParOldGen
 *      -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC
 *
 * 4.1 PSYoungGen + ParOldGen
 *      -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelOldGC
 *
 * 4.2 PSYoungGen + ParOldGen
 *      -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags
 */

public class GCDemo {
}
