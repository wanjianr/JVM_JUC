package com.douye.thread;

import java.util.concurrent.TimeUnit;

/**
 * 2020年5月10日17:45:25
 * 1. 普通方法与同步锁无关
 * 2. 调用同一对象的两个synchronized方法时，当执行其中一个synchronized时会锁住该对象，直到该方法执行完才能执行其他同步代码块
 * 3. 两个对象对应两个锁，对应的同步方法互不干扰
 * 4. static synchronized修饰的方法执行时，其他(对象)static synchronized修饰的方法则必须等到该方法执行完才能执行
 * 5. static synchronized修饰的方法不影响synchronized修饰的方法和普通方法的执行
 */
class Phone {
    public synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("--------开始发送邮件");
    }
    public synchronized void saveEmail() {
        System.out.println("正在接收邮件---------");
    }
    public void hello() {
        System.out.println("-----欢迎使用------");
    }
}

public class staticSyntacticDemo04 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();
        new Thread(()->{
            try {
                phone1.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"send").start();
        TimeUnit.MICROSECONDS.sleep(100);
        new Thread(()->{phone1.hello();phone1.saveEmail();},"save").start();
    }
}
