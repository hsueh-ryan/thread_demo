package com.example.thread_demo.notify;

public class WaitAndNotify {


    //驗證Ａ線程3秒後被喚醒

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread threadA = new Thread(() -> {
            try {
                synchronized (lock) {
                    System.out.println("start wait time= " + System.currentTimeMillis());
                    lock.wait();
                    System.out.println("end wait time= " + System.currentTimeMillis());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (lock) {
                System.out.println("start notify time= " + System.currentTimeMillis());
                lock.notify();
                System.out.println("edn notify time= " + System.currentTimeMillis());
            }
        });


        threadA.start();
        Thread.sleep(3000);
        threadB.start();
    }
}
