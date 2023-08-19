package com.example.thread_demo.reentrant_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CompleteReentrantLockDemo {

    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();

        Thread threadA = new Thread(myService::MethodA);
        threadA.setName("A");
        threadA.start();
        Thread threadAA = new Thread(myService::MethodA);
        threadAA.setName("AA");
        threadAA.start();

        Thread.sleep(100);


        Thread threadB = new Thread(myService::MethodB);
        threadB.setName("B");
        threadB.start();
        Thread threadBB = new Thread(myService::MethodB);
        threadBB.setName("BB");
        threadBB.start();


    }


    static class MyService {
        private final Lock lock = new ReentrantLock();

        public void MethodA() {
            try {
                lock.lock();
                System.out.println("methodA begin thread name = " + Thread.currentThread().getName() + " time= " + System.currentTimeMillis());
                Thread.sleep(5000);
                System.out.println("methodA end thread name = " + Thread.currentThread().getName() + " time= " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

        public void MethodB() {
            try {
                lock.lock();
                System.out.println("methodB begin thread name = " + Thread.currentThread().getName() + "time= " + System.currentTimeMillis());
                Thread.sleep(5000);
                System.out.println("methodB end thread name = " + Thread.currentThread().getName() + "time= " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
