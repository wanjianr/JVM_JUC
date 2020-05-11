package com.douye.jvm;

/**
 * 1. 本地方法
 * 2. 栈管运行，堆管存储
 * 3. 栈中存储的内容：8种基本类型的变量、引用变量、实例方法
 * 4. 异常/报错：Throwable子类：{Error，Exception}
 */
public class NativeClassDemo03 {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start(); // 启动时使用了start0()的native方法,在本地方法栈中
        //thread.start(); // java.lang.IllegalThreadStateException
    }
}
