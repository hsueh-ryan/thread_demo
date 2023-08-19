package com.example.thread_demo.reentrant_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    public static void main(String[] args) {
        MyService myService = new MyService();

        Thread t1 = new Thread(myService::testMethod);
        Thread t2 = new Thread(myService::testMethod);
        Thread t3 = new Thread(myService::testMethod);
        Thread t4 = new Thread(myService::testMethod);
        Thread t5 = new Thread(myService::testMethod);


        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

    static class MyService {
        private Lock lock = new ReentrantLock();
        public void testMethod() {
            try {
                lock.lock();
                for (int i = 0; i < 5; i++) {
                    System.out.println("TheadName = " + Thread.currentThread().getName() + "  " + (i + 1));
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
