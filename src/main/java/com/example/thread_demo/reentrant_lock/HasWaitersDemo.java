package com.example.thread_demo.reentrant_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class HasWaitersDemo {

    /*
        lock.hasWaiters(condition)可以查看是否有線程使用這個condition執行await而處於waiting狀態
     */

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(service::waitMethod);
            thread.start();
        }
        Thread.sleep(2000);
        service.notifyMethod();
    }

    static class Service{
        private final ReentrantLock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();

        public void waitMethod(){
            try {
                lock.lock();
                condition.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }

        public void notifyMethod(){
            try {
                lock.lock();
                System.out.println("Is any the condition wait for the lock = " + lock.hasWaiters(condition) +" count " + lock.getWaitQueueLength(condition));
                condition.signal();
                System.out.println("Is any thread use the condition wait = " + lock.hasWaiters(condition) +" count " + lock.getWaitQueueLength(condition));
            } finally {
                lock.unlock();
            }
        }


    }
}
