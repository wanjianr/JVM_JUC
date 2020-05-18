package com.douye.interview.reference;

/**
 * 2020年5月18日10:47:58
 * 强引用：另死不屈（即使出现OOM也不会被回收）
 */
public class StrongReferenceDemo01 {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = o1;
        System.out.println(o1);
        System.out.println(o2);

        o1 = null;
        System.gc();
        System.out.println();
        System.out.println(o1);
        System.out.println(o2);
    }
}
