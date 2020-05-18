package com.douye.interview;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OOMDemo {
    public static void main(String[] args) {
        //OOMJavaHeapSpace();
        //OOMGCOverheadLimitExceeded();
        OOMDirectBufferMemory();

    }

    /**
     * Metaspac是方法区Hotspot中的实现，它与持久代的最大区别在于：Metaspace并不在虚拟机内存中
     * 而在本地内存中，即java8中，class metadata被存储在Metaspace的native memory
     *
     * 永久代存储了以下信息
     *  虚拟机加载的类信息
     *  常量池
     *  静态变量
     *  即时编译后的代码
     *
     *  模拟Metaspace空间溢出，可以不断生成类往元空间方，类占据的空间会超过
     *  Metaspace指定的空间大小
     */

    /**
     * 2020年5月18日17:35:13
     *
     * 高并发请求服务器时，经常会出现如下异常unable create new native thread
     * 准确的将是native thread异常与对应的平台有关
     *
     * 导致原因：
     *      1. 应用中创建了太多线程，一个应用进程创建多个线程，超过系统承载极限
     *      2. 服务器不允许应用程序创建这么多线程，linux系统默认允许单个进程可以
     *      创建的线程数是1024个，如果超过这个数量就会报OOM
     *
     * 解决方法：
     *      1. 想办法降低应用程序创建线程的数量，分析应用是否有必要创建这么多线程
     *      如果不是，改代码减少线程数
     *      2. 修改linux服务器配置，扩大默认限制
     *
     */
    /**
     * 2020年5月18日17:01:21
     * 参数设置： -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
     *
     * 导致原因：
     *      写NIO程序经常使用到ByteBuffer来读取或者写入数据，这是一种基于通道（Channel）
     *      与缓冲区(Buffer)的I/O方式，它可以使用Native函数库直接分配堆外内存，然后通过
     *      一个存储在java堆里的DirectByteBuffer对象作为这块内存的引用进行操作。
     *      这样能在一些场景中显著提高性能，因为避免了在Java堆和Native堆中来回复制数据
     *
     *      ByteBuffer。allocate(capability) 分配JVM内存，属于GC管辖范围，由于需要拷贝
     *      所以速度相对较慢
     *
     *      ByteBuffer。allocateDirect(capability)分配OS本地内存，不属于GC管辖范围
     *      由于不需要内存拷贝所以速度较快
     *
     *      但如果不断分配本地内存，堆内存很少使用，那么JVM就不需要执行GC,DirectByteBuffer
     *      对象们就不会被回收，这时堆内存充足，但本地内存可能已经使用光了，再次尝试分配本地内存
     *      就会出现OOM
     */
    private static void OOMDirectBufferMemory() {
        // Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
        System.out.println("MaxDirectMemory:"+(sun.misc.VM.maxDirectMemory())/(double)1024/1024+"MB");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6*1024*1024);
    }


    /**
     * 参数设置： -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
     */
    private static void OOMGCOverheadLimitExceeded() {
        // java.lang.OutOfMemoryError: GC overhead limit exceeded
        int i = 0;
        List<String> arrayList = new ArrayList<>();
        try {
            while (true) {
                arrayList.add(String.valueOf(++i).intern());
            }
        } catch (Throwable e) {
            System.out.println("**********i:"+i);
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 参数设置： -Xms8m -Xmx8m
     */
    private static void OOMJavaHeapSpace() {
        // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        String str = "douyr";
        while (true) {
            str += str + new Random().nextInt(999999999) + new Random().nextInt(888888888);
            //str.intern();
        }
    }
}
