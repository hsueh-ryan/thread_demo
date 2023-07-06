package com.example.thread_demo.park;

import java.util.concurrent.locks.LockSupport;

public class ThreadPark {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("begin" + System.currentTimeMillis());
            LockSupport.park();
            System.out.println("  end" + System.currentTimeMillis());
        });

        thread.start();

        Thread.sleep(4000);
        LockSupport.unpark(thread);
    }
}
