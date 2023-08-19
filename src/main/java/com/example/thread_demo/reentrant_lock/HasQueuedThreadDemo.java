package com.example.thread_demo.reentrant_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class HasQueuedThreadDemo {

    /*
        lock.hasQueuedThread(threadA)可以查看threadA當前是否在等待獲取此鎖
        lock.hasQueuedThreads()可以查看當前是否有線程在等待獲取此鎖
     */

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();

        Thread threadA = new Thread(service::waitMethod);
        threadA.start();

        Thread.sleep(500);

        Thread threadB = new Thread(service::waitMethod);
        threadB.start();

        Thread.sleep(500);
        System.out.println("Is thread A waiting for lock = " + service.lock.hasQueuedThread(threadA));
        System.out.println("Is thread B waiting for lock = " + service.lock.hasQueuedThread(threadB));
        System.out.println("Is any thread waiting for lock = " + service.lock.hasQueuedThreads());
    }

    static class Service{
        private final ReentrantLock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();

        public void waitMethod(){
            try {
                lock.lock();
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }

        }

    }
}
