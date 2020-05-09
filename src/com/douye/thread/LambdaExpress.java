package com.douye.thread;

/**
 * 2020年5月9日17:54:49
 * 函数式编程
 * lambda表达式知识点
 * 1. 使用格式：小括号([args],[...]) -- 右箭头-> -- 大括号
 * 2. @FunctionalInterface 函数编程默认注解
 * 3. 接口中可以定义多个default默认方法
 * 4. 接口中可以定义多个静态方法
 */
@FunctionalInterface
interface Foo {
    public int add(int x,int y);
    //public int sum();
    default int mul(int x, int y) {
        return x*y;
    }

    public static int div (int x, int y) {
        return x/y;
    }
}
public class LambdaExpress {
    public static void main(String[] args) {
        Foo foo = (int x, int y) -> {return x+y;};
        System.out.println("Add:"+foo.add(2,3)+"\tMul:"+foo.mul(2,3)+"\tDiv:"+Foo.div(2,3));
    }
}
