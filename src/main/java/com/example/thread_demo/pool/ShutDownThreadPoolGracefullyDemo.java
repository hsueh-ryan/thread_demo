package com.example.thread_demo.pool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ShutDownThreadPoolGracefullyDemo {

    /*
    boolean awaitTermination(long timeout, TimeUnit unit)
    此方法會組塞調用線程,直到所有任務在指定時間內完成或者超時,如果任務在指定時間內完成返回true否則返回false
    可以使用awaitTermination來等待線程中的任務執行完畢
     */

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2), new SimpleThreadFactory(), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println(Thread.currentThread().getName() + " rejected" + " - getTaskCount: " + executor.getTaskCount());
            }
        });

        for (int i = 0; i < 10; i++) {
            pool.execute(new TargetTask());
        }

        shutDownThreadPoolGracefully(pool);

    }

    static class SimpleThreadFactory implements ThreadFactory {
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

    static class TargetTask implements Runnable {
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
                Thread.sleep(5500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(taskName + "運行結束");
        }
    }

    public static void shutDownThreadPoolGracefully(ExecutorService pool) {
        if (pool.isTerminated()) {
            System.out.println("[ShutDownThreadPoolGracefully]  已關閉返回");
            return;
        }

        pool.shutdown();
        System.out.println("[ShutDownThreadPoolGracefully] pool shutDown 拒絕新任務");
        try {
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow();
                System.out.println("[ShutDownThreadPoolGracefully] pool shutdownNow 中斷任務");
                if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.out.println("[ShutDownThreadPoolGracefully] 線程池任務未正常執行結束");
                }
            }
        } catch (InterruptedException e) {
            System.out.println("[ShutDownThreadPoolGracefully] 補獲異常,重新調用shutdownNow方法");
            pool.shutdownNow();
        }

        if (!pool.isTerminated()) {
            System.out.println("[ShutDownThreadPoolGracefully] 仍然沒有關閉,");
            //設定重試次數
            try {
                for (int i = 0; i < 1000; i++) {
                    if (pool.awaitTermination(10, TimeUnit.MILLISECONDS)) {
                        break;
                    }
                    pool.shutdownNow();
                }
            } catch (Throwable e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("[ShutDownThreadPoolGracefully] shut down finish");
    }
}
