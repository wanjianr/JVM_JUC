package com.douye.interview.reference;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 2020年5月18日10:59:39
 * 弱引用：不管内存够不够，只要发生垃圾回收，一律回收
 */
public class WeakReferenceDemo03 {
    public static void main(String[] args) {

        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());

        o1 = null;
        System.gc();
        System.out.println();
        System.out.println(o1);
        System.out.println(weakReference.get());
    }
}
