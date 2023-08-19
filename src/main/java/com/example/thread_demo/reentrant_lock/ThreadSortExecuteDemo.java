package com.example.thread_demo.reentrant_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSortExecuteDemo {

    /*
        實現順序執行
     */
    public static void main(String[] args) {
        Service service = new Service();

        for (int i = 0; i < 5; i++) {
            new Thread(service::method1).start();
            new Thread(service::method2).start();
            new Thread(service::method3).start();
        }
    }


    static class Service {
        private final Lock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();
        volatile int nextPrint = 1;


        public void method1() {
            try {
                lock.lock();
                while (nextPrint != 1) {
                    condition.await();
                }
                System.out.println("AAA");
                nextPrint = 2;
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void method2() {
            try {
                lock.lock();
                while (nextPrint != 2) {
                    condition.await();
                }
                System.out.println("   BBB");
                nextPrint = 3;
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void method3() {
            try {
                lock.lock();
                while (nextPrint != 3) {
                    condition.await();
                }
                System.out.println("      CCC");
                nextPrint = 1;
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
