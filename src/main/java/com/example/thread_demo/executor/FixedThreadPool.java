package com.example.thread_demo.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedThreadPool {

    /*
        創建固定線程數量的線程池
        1. 如果線程還沒到固定數量,每次提交一個任務線程池就會創建一個新線程,直到線程到達固定數量
        2. 線程池的大小到達固定數量就會保持不變,如果某個線程因執行異常而結束,線程池會補充一個新線程
        3. 在接收提交任務時,如果池中的所有線程均在繁忙狀態,新任務會進入阻塞隊列中(無界的組塞隊列)
     */

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 5; i++) {
            pool.execute(new TargetTask());
            pool.submit(new TargetTask());
        }

        Thread.sleep(1000);
        pool.shutdown();
    }


    static class TargetTask implements Runnable{
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
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(taskName + "運行結束");
        }
    }
}
