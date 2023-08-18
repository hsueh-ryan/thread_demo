package com.example.thread_demo.wait_signal;

import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WaitSignalTwoConditionDemo {


    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();

        Thread threadA = new Thread(myService::awaitA);
        threadA.setName("A");
        threadA.start();

        Thread threadB = new Thread(myService::awaitB);
        threadB.setName("B");
        threadB.start();

        Thread.sleep(3000);

        myService.signalAllA();

    }

    /**
     * 一個鎖物件可以創建出多個Condition物件
     * 可以由不同condition物件來決定notify哪個wait
     */

    static class MyService{
        private final Lock lock = new ReentrantLock();
        private final Condition conditionA = lock.newCondition();
        private final Condition conditionB = lock.newCondition();

        public void awaitA(){
            try {
                lock.lock();
                System.out.println("thread name = "+Thread.currentThread().getName() + " start awaitA time = " + System.currentTimeMillis());
                conditionA.await();
                System.out.println("thread name = "+Thread.currentThread().getName() + " end awaitA time = " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }

        public void awaitB(){
            try {
                lock.lock();
                System.out.println("thread name = "+Thread.currentThread().getName() + " start awaitB time = " + System.currentTimeMillis());
                conditionB.await();
                System.out.println("thread name = "+Thread.currentThread().getName() + " end awaitB time = " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }

        public void signalAllA(){
            try {
                lock.lock();
                System.out.println("thread name = "+Thread.currentThread().getName() + "  signalAllA time = " + System.currentTimeMillis());
                conditionA.signalAll();
            } finally {
                lock.unlock();
            }
        }

        public void signalAllB(){
            try {
                lock.lock();
                System.out.println("thread name = "+Thread.currentThread().getName() + "  signalAllB time = " + System.currentTimeMillis());
                conditionB.signalAll();
            } finally {
                lock.unlock();
            }
        }



    }
}
