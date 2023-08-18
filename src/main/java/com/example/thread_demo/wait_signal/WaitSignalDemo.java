package com.example.thread_demo.wait_signal;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WaitSignalDemo {

    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();

        Thread thread = new Thread(myService::await);
        thread.start();

        Thread.sleep(3000);

        myService.signal();
    }


    static class MyService {
        private Lock lock = new ReentrantLock();
        public Condition condition = lock.newCondition();

        public void await() {
            try {
                lock.lock();
                System.out.println("await time = " + System.currentTimeMillis());
                condition.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }

        public void signal() {
            try {
                lock.lock();
                System.out.println("signal time = " + System.currentTimeMillis());
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
