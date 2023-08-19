package com.example.thread_demo.reentrant_lock;

import java.util.Calendar;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitUntilDemo {

    /*
        condition.awaitUntil(calendar.getTime());可以指定日期結束等待
        但如果有被signal()一樣會提早喚醒
     */

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();

        Thread threadA = new Thread(service::waitMethod);
        threadA.start();

        Thread.sleep(1000);

        Thread threadB = new Thread(service::notifyMethod);
        threadB.start();
    }


    static class Service {
        private final ReentrantLock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();

        public void waitMethod() {
            try {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.SECOND, 3);

                lock.lock();
                System.out.println("wait start time = " + System.currentTimeMillis());
                condition.awaitUntil(calendar.getTime());
                System.out.println("wait start end = " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void notifyMethod() {
            try {
                lock.lock();
                System.out.println("notify start time = " + System.currentTimeMillis());
                condition.signalAll();
                System.out.println("notify start end = " + System.currentTimeMillis());
            } finally {
                lock.unlock();
            }
        }
    }
}
