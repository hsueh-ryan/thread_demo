package com.example.thread_demo.stop_thread;

public class InterruptedAndIsInterrupted {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (; ; ) ;
        });

        thread.start();
        //設置中斷標誌
        thread.interrupt();

        System.out.println("isInterrupted:" + thread.isInterrupted());

        //因為主線程是當前線程（thread.interrupted()與Thread.interrupted()是一樣的）
        System.out.println("isInterrupted:" + thread.interrupted());
        System.out.println("isInterrupted:" + Thread.interrupted());

        System.out.println("isInterrupted:" + thread.isInterrupted());

        thread.join();
        System.out.println("main thread is over");



    }
}
