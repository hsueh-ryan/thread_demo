package com.example.thread_demo.reentrant_lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockByReadWriteDemo {

    /*
        讀寫互斥
        說明讀鎖與寫鎖互斥,讀鎖被獲取,寫鎖獲取需等待讀鎖釋放
     */

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();

        Thread threadA = new Thread(service::read);
        threadA.setName("A");
        threadA.start();

        Thread.sleep(1000);

        Thread threadB = new Thread(service::write);
        threadB.setName("B");
        threadB.start();
    }


    static class Service {
        private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        public void read() {
            try {
                lock.readLock().lock();
                System.out.println("start read lock thread name = " + Thread.currentThread().getName() + " time = " + System.currentTimeMillis());
                Thread.sleep(4000);
                System.out.println("end read lock thread name = " + Thread.currentThread().getName() + " time = " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.readLock().unlock();
            }
        }

        public void write() {
            try {
                lock.writeLock().lock();
                System.out.println("start write lock thread name = " + Thread.currentThread().getName() + "t ime = " + System.currentTimeMillis());
                Thread.sleep(4000);
                System.out.println("end write lock thread name = " + Thread.currentThread().getName() + " time = " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.writeLock().unlock();
            }
        }
    }
}
