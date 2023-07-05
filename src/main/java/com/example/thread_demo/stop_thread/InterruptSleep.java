package com.example.thread_demo.stop_thread;

public class InterruptSleep {
    public static void main1(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("run begin");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                //在sleep狀態下被中斷,會拋出異常並且重置標籤 所以標籤狀態回傳false
                System.out.println("睡眠終止" + Thread.currentThread().isInterrupted());
                e.printStackTrace();
            }
        });

        thread.start();
        Thread.sleep(100);
        thread.interrupt();

        System.out.println("end");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i < 100000; i++) {
                    System.out.println("i=" + (i + 1));
                }
                System.out.println("run begin");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                //執行中斷標籤後 在sleep也會拋出異常
                System.out.println("下中斷標籤後遇到sleep 拋出異常");
                e.printStackTrace();
            }
        });

        thread.start();
        thread.interrupt();
        System.out.println("執行中斷標籤");

        System.out.println("end");
    }
}
