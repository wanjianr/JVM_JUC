package com.douye.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 2020年5月9日16:41:42
 * 高内聚低耦合思想：线程--操作--资源类
 */

class Ticket{
    private int ticket = 30;
    public void saleTicket() {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            if (ticket > 0)
                System.out.println(Thread.currentThread().getName()+"\t出售票号："+(ticket--)+"\t余票数："+ticket);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class SaleTicketDemo01 {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        System.out.println("-------主线程开启--------");
        new Thread(()->{ for (int i = 0; i < 40; i++) { ticket.saleTicket(); } },"AA").start();
        new Thread(()->{ for (int i = 0; i < 40; i++) { ticket.saleTicket(); } },"BB").start();
        new Thread(()->{ for (int i = 0; i < 40; i++) { ticket.saleTicket(); } },"CC").start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 40; i++) {
//                    ticket.saleTicket();
//                }
//            }
//        }, "A").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 40; i++) {
//                    ticket.saleTicket();
//                }
//            }
//        }, "B").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 40; i++) {
//                    ticket.saleTicket();
//                }
//            }
//        }, "C").start();

        System.out.println("-------主线程结束--------");
    }
}
