package com.douye.thread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * ArrayList，set，map线程不安全，在并发场景中会报并发修改异常：java.util.ConcurrentModificationException
 * 解决方法：
 * 1. Vector是线程安全的
 * 2. 使用工具类Collections.synchronizedList(new ArrayList<>());
 * 3. 写时复制new CopyOnWriteArrayList<>();
 */
public class UnSafeDemo03 {
    public static void main(String[] args) {
        mapUnsafe();
    }

    private static void mapUnsafe() {
        Map<String,String> map = new ConcurrentHashMap<>();//new HashMap<>();
        for (int i = 1; i < 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName().toString(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

    private static void setUnsafe() {
        Set<String> set = new CopyOnWriteArraySet<>();//new HashSet<>();
        for (int i = 1; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }

    private static void listUnsafe() {
        List<String> list = new CopyOnWriteArrayList<>();//Collections.synchronizedList(new ArrayList<>());//Vector<>();//ArrayList<>();
        for (int i = 1; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
        //list.forEach(System.out::print);
    }
}
