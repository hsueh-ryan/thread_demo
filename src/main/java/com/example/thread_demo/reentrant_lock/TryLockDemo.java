package com.example.thread_demo.reentrant_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockDemo {

    /*
        lock.tryLock() => 返回布林值,取不到鎖不會阻塞進入等待狀態，而會執行後面的代碼
        lock.tryLock(3, TimeUnit.SECONDS)
     */

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();

        Thread threadA = new Thread(() -> {
            System.out.println("thread name = " + Thread.currentThread().getName() + " 調用waitMethod time = " + System.currentTimeMillis());
            service.waitMethod();
        });
        threadA.setName("A");
        threadA.start();

        Thread threadB = new Thread(() -> {
            System.out.println("thread name = " + Thread.currentThread().getName() + " 調用waitMethod time = " + System.currentTimeMillis());
            service.waitMethod();
        });
        threadB.setName("B");
        threadB.start();
    }

    static class Service {
        private final ReentrantLock lock = new ReentrantLock();

        public void waitMethod() {
            try {
                if (lock.tryLock(3, TimeUnit.SECONDS)) {
                    System.out.println("have lock thread name = " + Thread.currentThread().getName() + " time = " + System.currentTimeMillis());
                    Thread.sleep(4000);
                } else {
                    System.out.println("have no lock thread name = " + Thread.currentThread().getName() + " time = " + System.currentTimeMillis());
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                //因為有可能會沒有取到鎖,所以要判斷有取到鎖的在釋放鎖
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }
    }
}
