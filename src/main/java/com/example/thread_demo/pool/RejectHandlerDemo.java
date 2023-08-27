package com.example.thread_demo.pool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RejectHandlerDemo {

    /*
        一次提交10個任務
        先創建兩條核心線程
        然後把queue存滿兩個任務
        再創建線程達到maxNum來處理無法存入queue裡的提交任務
        此時接收了六個任務
        剩餘四個會走拒絕策略
     */

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2),new SimpleThreadFactory(), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println(Thread.currentThread().getName() + " rejected" + " - getTaskCount: " + executor.getTaskCount());
            }
        });

        for (int i = 0; i < 10; i++) {
            pool.execute(new TargetTask());
        }

        Thread.sleep(5000);

        pool.shutdown();

    }

    static class SimpleThreadFactory implements ThreadFactory{
        static AtomicInteger threadNo = new AtomicInteger(1);
        @Override
        public Thread newThread(Runnable r) {
            String threadName = "simpleThread- " + threadNo.get();
            System.out.println("創建一條線程, 名稱為: " + threadName);
            threadNo.incrementAndGet();
            Thread thread = new Thread(r, threadName);
            thread.setDaemon(false);
            return thread;
        }
    }

    static class TargetTask implements Runnable{
        static AtomicInteger taskNo = new AtomicInteger(1);
        public final String taskName;

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
