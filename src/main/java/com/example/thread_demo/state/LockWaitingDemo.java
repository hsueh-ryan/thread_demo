package com.example.thread_demo.state;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockWaitingDemo {

    /*
        執行await的線程狀態為WAITING
     */


    public static void main(String[] args) throws InterruptedException {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        Condition condition = lock.writeLock().newCondition();
        Thread thread = new Thread(() -> {
            System.out.println("thread start");
            try {
                lock.writeLock().lock();
                condition.await();
                System.out.println("thread finish");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.writeLock().unlock();
            }
        });

        thread.start();

        Thread.sleep(1000);

        System.out.println("main call locking thread state = " + thread.getState());

        Thread.sleep(1000);

        try {
            lock.writeLock().lock();
            condition.signal();
        } finally {
            lock.writeLock().unlock();
        }
    }


}
