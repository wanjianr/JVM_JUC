package com.douye.interview.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;


/**
 * 2020年5月18日15:00:59
 *
 * 总结：
 *      java的四种引用类型，在垃圾回收的时候都有自己各自的特点
 *      ReferenceQueue是用来配合引用工作的，没有ReferenceQueue一样也可以
 *
 *      创建引用的时候可以指定关联的队列，当GC释放对象内存的时候，会将引用加入到引用队列
 *
 *      如果程序发现某个虚引用已经被加入到引用队列，那么就可以在所引用的对象的内存被回收
 *      之前采取必要的行动，这可以理解为是一种通知机制
 *
 *      当关联的引用队列中有数据的时候，意味着引用指向的堆内存中对象被回收，通过这种方式，
 *      JVM允许我们在对象被销毁后，做一件我们自己想做的事
 */
public class PhantomReferenceDemo06 {
    public static void main(String[] args) {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1,referenceQueue);
        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

        o1 = null;
        System.gc();
        System.out.println();
        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
    }
}
