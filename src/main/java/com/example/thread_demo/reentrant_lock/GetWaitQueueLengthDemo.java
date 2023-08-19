package com.example.thread_demo.reentrant_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class GetWaitQueueLengthDemo {

         /*
            lock.getWaitQueueLength(condition)可以查看當前有多少線程使用該condition在wait狀態
            signal觸發後就會-1不需要等到signal線程執行完成
         */

    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(myService::waitMethod);
            thread.start();
        }

        Thread.sleep(2000);

        myService.notifyMethod();
    }

    static class MyService{
        private final ReentrantLock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();

        public void waitMethod(){
            try {
                lock.lock();
                condition.await();
                System.out.println("finish");
                System.out.println("thread condition wait count = " + lock.getWaitQueueLength(condition));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }

        public void notifyMethod(){
            try {
                lock.lock();
                System.out.println("thread condition wait count = " + lock.getWaitQueueLength(condition));
                condition.signal();
                System.out.println("thread condition wait count = " + lock.getWaitQueueLength(condition));
            } finally {
                lock.unlock();
            }
        }


    }
}
