package com.example.thread_demo.volatile_test;

public class ThreadOne extends Thread {
    volatile public static int count;

    private synchronized static void addCount() {
        for (int i = 0; i < 100; i++) {
            count++;
        }
        System.out.println("count=" + count);
    }

    @Override
    public void run() {
        addCount();
    }
}
