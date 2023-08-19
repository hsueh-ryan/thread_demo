package com.example.thread_demo.reentrant_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class LockInterruptiblyDemo {

    /*
        lock.lockInterruptibly()=>當某個線程正在等待獲取此鎖時可以用interrupt中斷,如果用lock.lock()則無法中斷

     */

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();

        Thread thread = new Thread(service::testMethod);
        thread.setName("A");
        thread.start();

        Thread.sleep(500);

        Thread threadB = new Thread(service::testMethod);
        threadB.setName("B");
        threadB.start();

        threadB.interrupt();
        System.out.println("中斷 thread");

    }

    static class Service {
        private final Lock lock = new ReentrantLock();

        public void testMethod() {
            try {
                lock.lockInterruptibly();

                System.out.println("thead start = " + Thread.currentThread().getName() + " time = " + System.currentTimeMillis());
                for (int i = 0; i < Integer.MAX_VALUE / 10; i++) {
                    Math.random();
                }

                System.out.println("thead end = " + Thread.currentThread().getName() + " time = " + System.currentTimeMillis());

            } catch (InterruptedException e) {
                throw new RuntimeException("被中斷");
            } finally {
                lock.unlock();
            }
        }
    }
}
