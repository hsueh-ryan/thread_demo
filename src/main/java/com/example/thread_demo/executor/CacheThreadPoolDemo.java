package com.example.thread_demo.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class CacheThreadPoolDemo {

    /*
        可緩存線程池
        1. 接收新任務時,若池內所有線程池繁忙,線程池會創建新線程處理任務
        2. 此線程不會對線程大小進行限制,線程池大小完全依賴JVM能夠創建的最大線程大小
        3. 若部分線程空閑,也就是線程數量超過了處理任務數量,就會回收空閒(60秒不執行任務)線程
     */

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            pool.execute(new TargetTask());
            pool.submit(new TargetTask());
        }

        Thread.sleep(5000);
//        pool.shutdown();
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
