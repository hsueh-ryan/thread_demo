package com.example.thread_demo.reentrant_lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantReadWriteLockByReadDemo {

    /*
        讀讀共享
        如果使用ReentrantLock鎖則必須獲取鎖才能進入
        使用ReentrantReadWriteLock讀鎖則為共享鎖,兩條線程皆為讀不需要獲取鎖就可進入
        因為避免還有寫進入所以依然需要鎖
     */
    public static void main(String[] args) {
        Service service = new Service();

        new Thread(service::method1).start();
        new Thread(service::method1).start();
    }


    static class Service {
        //        private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        private final ReentrantLock lock = new ReentrantLock();
        private String username = "ABC";

        public void method1() {
            try {
//                lock.readLock().lock();
                lock.lock();
                System.out.println("start thread name = " + Thread.currentThread().getName() + "time = " + System.currentTimeMillis());
                System.out.println("print service " + username);
                Thread.sleep(4000);
                System.out.println("end thread name = " + Thread.currentThread().getName() + "time = " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
//                lock.readLock().unlock();
                lock.unlock();
            }
        }
    }
}
