package com.example.thread_demo.reentrant_lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockByWriteDemo {

    /*
        寫寫互斥
        使用寫鎖則必須獲取鎖才能進入
     */

    public static void main(String[] args) {
        Service service = new Service();

        new Thread(service::method1).start();
        new Thread(service::method1).start();
    }


    static class Service {
        private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        public void method1() {
            try {
                lock.writeLock().lock();
                System.out.println("start write lock thread name = " + Thread.currentThread().getName() + "time = " + System.currentTimeMillis());
                Thread.sleep(4000);
                System.out.println("end write lock thread name = " + Thread.currentThread().getName() + "time = " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.writeLock().unlock();
            }
        }
    }
}
