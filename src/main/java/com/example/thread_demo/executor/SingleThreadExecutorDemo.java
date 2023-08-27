package com.example.thread_demo.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class SingleThreadExecutorDemo {

    /*
        newSingleThreadExecutor
        1. 單線程化的線程池任務會按照提交的順序執行
        2. 池中唯一的線程生存時間是無限的
        3. 線程繁忙時,新提交的任務會進入內部的阻塞對列中,阻塞對列是無界的
        4. 調用shutdown()來關閉線程池,線程池狀態變為SHUTDOWN狀態,此時線程將會拒絕新任務,不能在往池中添加新任務,反則會拋出RejectedExecutionException異常
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newSingleThreadExecutor();

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
