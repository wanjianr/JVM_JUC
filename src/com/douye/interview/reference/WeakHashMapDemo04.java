package com.douye.interview.reference;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * 2020年5月18日14:26:17
 * HashMap是强引用
 * WeakHashMap是弱引用，当其中的ke为null时，在垃圾回收后该key对应的键值将会消失y
 */
public class WeakHashMapDemo04 {
    public static void main(String[] args) {
        myHashMap();
        System.out.println("================");
        myWeakHashMap();
    }

    private static void myWeakHashMap() {
        WeakHashMap<Integer,String> hashMap = new WeakHashMap<>();
        Integer key = new Integer(10);
        String value = "weakHashMap";
        hashMap.put(key,value);
        System.out.println(hashMap);

        key = null;
        System.out.println("key=null垃圾回收前" + hashMap);
        System.gc();
        System.out.println();
        System.out.println("key=null垃圾回收后" + hashMap);
    }

    private static void myHashMap() {
        HashMap<Integer,String> hashMap = new HashMap<>();
        Integer key = new Integer(1);
        String value = "hashMap";
        hashMap.put(key,value);
        System.out.println(hashMap);

        key = null;
        System.out.println("key=null垃圾回收前" + hashMap);
        System.gc();
        System.out.println();
        System.out.println("key=null垃圾回收后" + hashMap);
    }
}
