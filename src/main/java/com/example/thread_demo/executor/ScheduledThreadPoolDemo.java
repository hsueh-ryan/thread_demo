package com.example.thread_demo.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class ScheduledThreadPoolDemo {


    //TODO scheduleAtFixedRate使用卻是等到前個任務完成後才開始執行？
    /*
        執行延時與週期性任務可調度線程池
        1. scheduleAtFixedRate無論上個任務是否完成,以固定時間間隔執行任務,如果任務執行時間較長,可能會導致任務之間的重疊
        2. scheduleWithFixedDelay等到任務完成後,等待固定的間隔在開始下次任務,確保任務之間有一定的間隔,不會重疊
     */

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);


        for (int i = 0; i < 2; i++) {
//            pool.scheduleAtFixedRate(new TargetTask(),0,500, TimeUnit.MILLISECONDS);

            pool.scheduleWithFixedDelay(new TargetTask(), 0, 500, TimeUnit.MILLISECONDS);
        }

        Thread.sleep(10000);
        pool.shutdown();
    }

    static class TargetTask implements Runnable {
        static AtomicInteger taskNo = new AtomicInteger(1);
        private final String taskName;

        public TargetTask() {
            this.taskName = "task-" + taskNo.get();
            taskNo.incrementAndGet();
        }

        @Override
        public void run() {
            System.out.println("task: " + taskName + "doing");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(taskName + "運行結束");
        }
    }
}
