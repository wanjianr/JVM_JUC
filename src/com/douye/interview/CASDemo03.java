package com.douye.interview;

import javax.jws.soap.SOAPBinding;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
/**
 * CAS ：比较并保存,依赖于Unsafe类
 *  如期望值与主内存中的值一致，则更新
 *
 *  CAS -- Unsafe类 -- CAS底层思想 -- ABA问题 -- 原子引用更新 -- 如何避免ABA问题
 */
public class CASDemo03 {
    public static void main(String[] args) {
        //CAS_display();
        ABA_problem();

        //ABA_solve();
    }

    private static void ABA_solve() {
        User user1 = new User("zhangsan",18);
        User user2 = new User("lisi",20);
        AtomicStampedReference<User> atomicStampedReference = new AtomicStampedReference<>(user1,1);
        new Thread(()->{
            System.out.println(atomicStampedReference.getReference());
            atomicStampedReference.compareAndSet(user1,user2,1,atomicStampedReference.getStamp()+1);
            System.out.println(atomicStampedReference.getReference());
            atomicStampedReference.compareAndSet(user2,user1,2,atomicStampedReference.getStamp()+1);
            System.out.println(atomicStampedReference.getReference());
        },"A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            System.out.println(atomicStampedReference.getReference());
            atomicStampedReference.compareAndSet(user1,user2,1,atomicStampedReference.getStamp()+1);
            System.out.println(atomicStampedReference.getReference());
        },"B").start();
    }

    private static void ABA_problem() {
        User user1 = new User("zhangsan",18);
        User user2 = new User("lisi",20);
        AtomicReference<User> atomicReference = new AtomicReference<>(user1);
        new Thread(()->{
            System.out.println(atomicReference.get());
            System.out.println(atomicReference.compareAndSet(user1,user2));
            System.out.println(atomicReference.get());
            System.out.println(atomicReference.compareAndSet(user2,user1));
            System.out.println(atomicReference.get());
        },"A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            System.out.println(atomicReference.get());
            System.out.println(atomicReference.compareAndSet(user1,user2));
            System.out.println(atomicReference.get());
        },"B").start();
    }

    private static void CAS_display() {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(6,2020) + "\t" +atomicInteger.get());
    }
}
