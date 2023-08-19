package com.example.thread_demo.reentrant_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GetQueueLengthDemo {

    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(myService::testMethod1);
            thread.start();
        }
        Thread.sleep(2000);

        System.out.println("waiting thread count = "+ myService.lock.getQueueLength());
    }



    static class MyService{
        private final ReentrantLock lock = new ReentrantLock();

        public void testMethod1(){
            try {
                lock.lock();
                System.out.println("thread name = " + Thread.currentThread().getName() + "enter");
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }


    }
}
