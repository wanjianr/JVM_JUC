package com.douye.interview.reference;

import java.lang.ref.SoftReference;

/**
 * 2020年5月18日10:51:34
 * 软引用：内存不够时才会被回收，否则不回收
 */
public class SorftReferenceDemo02 {
    public static void main(String[] args) {
        memoryEnough();
        //memoryNotEnough();
    }

    private static void memoryEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        System.gc();
        System.out.println();
        System.out.println(o1);
        System.out.println(softReference.get());
    }

    /**
     * 设置堆内存：Xms5m Xmn5m
     */
    private static void memoryNotEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        try {
            byte[] bytes = new byte[10*1024*1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println();
            System.out.println(o1);
            System.out.println(softReference.get());
        }


    }
}
