package com.douye.jvm;

/**
 * 2020年5月11日13:27:08
 * 虚拟机自带的加载器
 * 启动类加载器（Bootstrap）C++
 * 扩展类加载器（Extension）Java
 * 应用程序类加载器（AppClassLoader）Java也叫系统类加载器，加载当前应用的classpath的所有类
 *
 * 用户自定义加载器  Java.lang.ClassLoader的子类，用户可以定制类的加载方式
 *
 * 双亲委派：父类能做就找父类，不能做再自己做
 */
public class ClassLoaderDemo01 {
    public static void main(String[] args) {
        Object object = new Object();   // 使用启动类加载器
        System.out.println(object.getClass()); // 获取实例模板，即方法去所存的模板
        System.out.println(object.getClass().getClassLoader()); // 获取模板所用的加载器，Bootstrap是根加载器，默认为null

        ClassLoaderDemo01 myClass = new ClassLoaderDemo01(); // 应用程序类加载器（AppClassLoader）
        System.out.println(myClass.getClass());
        System.out.println(myClass.getClass().getClassLoader().getParent().getParent()); // 启动类加载器（Bootstrap）
        System.out.println(myClass.getClass().getClassLoader().getParent());  // 扩展类加载器（Extension）
        System.out.println(myClass.getClass().getClassLoader()); // // 应用程序类加载器（AppClassLoader）
    }
}
