package com.example.thread_demo.stop_thread;

public class InterruptStop {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                System.out.println("i=" + (i + 1));
            }
        });
        thread.start();
        Thread.sleep(100);

        //無法成功停止執行緒
        thread.interrupt();

        System.out.println("zzzzzz");
    }



    public static void main3(String[] args) {
        Thread.currentThread().interrupt();

        System.out.println("是否停止 sub ? ="+Thread.interrupted());
        System.out.println("是否停止 sub ? ="+Thread.interrupted());

        System.out.println("end");

    }

    public static void main2(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 500000; i++) {
                System.out.println("i=" + (i + 1));
            }
        });
        thread.start();
        Thread.sleep(1000);

        thread.interrupt();

        System.out.println("是否停止 1 ? ="+thread.isInterrupted());
        System.out.println("是否停止 2 ? ="+thread.isInterrupted());

        System.out.println("end");

    }
}
