package com.example.thread_demo.reentrant_lock;

import java.util.concurrent.locks.ReentrantLock;

public class GetHoldCountDemo {

 /*
 getHoldCount可以查詢當前鎖定的個數
  */

    public static void main(String[] args) {
        MyService myService = new MyService();
        myService.testMethod1();
    }


    static class MyService {
        private final ReentrantLock lock = new ReentrantLock();

        public void testMethod1() {
            System.out.println("A " + lock.getHoldCount());
            lock.lock();
            System.out.println("B " + lock.getHoldCount());
            testMethod2();
            System.out.println("F " + lock.getHoldCount());
            lock.unlock();
            System.out.println("G " + lock.getHoldCount());
        }

        public void testMethod2() {
            System.out.println("C " + lock.getHoldCount());
            lock.lock();
            System.out.println("D " + lock.getHoldCount());
            lock.unlock();
            System.out.println("E " + lock.getHoldCount());
        }


    }
}

