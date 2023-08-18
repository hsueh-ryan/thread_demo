package com.example.thread_demo.thread_local;

public class IsolationDemo {

    /**
     * 驗證線程變量的隔離性
     * ThreadLocal在每個線程中存儲自己的私有數據
     */

    public static ThreadLocal<String> tl = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    tl.set("A " + (i + 1));
                    System.out.println("A get " + tl.get());
                    int sleepTime = (int) (Math.random() * 1000);
                    Thread.sleep(sleepTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    tl.set("B " + (i + 1));
                    System.out.println("    B get " + tl.get());
                    int sleepTime = (int) (Math.random() * 1000);
                    Thread.sleep(sleepTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        for (int i = 0; i < 10; i++) {
            tl.set("main " + (i + 1));
            System.out.println("              main get " + tl.get());
            int sleepTime = (int) (Math.random() * 1000);
            Thread.sleep(sleepTime);
        }

    }
}
