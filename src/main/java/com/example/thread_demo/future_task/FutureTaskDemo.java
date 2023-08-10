package com.example.thread_demo.future_task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {

    public static final int MAX_TURN=5;
    public static final int COMPUTE_TIMES=100000000;


    public static void main(String[] args) throws InterruptedException {
        //這裡使用函數式callable物件
        FutureTask<Long> futureTask = new FutureTask<>(() -> {
            long startTime = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + "線程運行開始");
            Thread.sleep(1000);
            for (int i = 0; i < COMPUTE_TIMES; i++) {
                int j = i * 10000;
            }
            long used = System.currentTimeMillis() - startTime;
            System.out.println(Thread.currentThread().getName() + "線程運行結束");
            return used;
        });
        Thread thread = new Thread(futureTask, "returnableThread");
        thread.start();
        Thread.sleep(500);
        System.out.println("do something...");
        for (int i = 0; i < COMPUTE_TIMES/2; i++) {
            int j = i*10000;
        }
        System.out.println(Thread.currentThread().getName()+"獲取併發任務的執行結果");

        try {
            System.out.println(thread.getName()+"線程佔用時間:"+ futureTask.get());
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        System.out.println("運行結束");
    }

}


