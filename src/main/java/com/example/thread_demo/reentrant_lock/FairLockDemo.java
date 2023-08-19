package com.example.thread_demo.reentrant_lock;

import java.util.concurrent.locks.ReentrantLock;


//TODO 可否保證啟動時間的獲取順序
public class FairLockDemo {

    /*
        如果使用非公平鎖每次都會先嘗試獲取鎖,獲取不到才會進入隊列等待
        lock.isFair()可以查看當前鎖是否為公平鎖
     */
    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService(true);

        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(myService::testMethod);
            thread.setName("group 1 +++" + (i + 1));
            thread.start();
        }

        Thread.sleep(50);

        for (int i = 0; i < 100; i++) {
            Thread thread2 = new Thread(myService::testMethod);
            thread2.setName("group 2 ---" + (i + 1));
            thread2.start();
        }
        System.out.println("Is the lock fair = " + myService.lock.isFair());
    }


    static class MyService {
        private final ReentrantLock lock;

        public MyService(boolean fair) {
            this.lock = new ReentrantLock(fair);
        }

        public void testMethod() {
            try {
                lock.lock();
                System.out.println("thread name = " + Thread.currentThread().getName());
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }
}
