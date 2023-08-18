package com.example.thread_demo.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


//TODO 非公平鎖沒有效果?
public class FairLockDemo {
    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService(false);

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(myService::testMethod);
            thread.setName("group 1 +++" + (i + 1));
            thread.start();
        }

        Thread.sleep(500);

        for (int i = 0; i < 10; i++) {
            Thread thread2 = new Thread(myService::testMethod);
            thread2.setName("group 2 ---" + (i + 1));
            thread2.start();
        }
    }


    static class MyService {
        private final Lock lock;

        public MyService(boolean fair) {
            this.lock = new ReentrantLock(fair);
        }

        public void testMethod() {
            try {
                lock.lock();
                System.out.println("thread name = " + Thread.currentThread().getName());
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }
}
