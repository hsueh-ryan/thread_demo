package com.example.thread_demo.future_task;

import java.util.concurrent.*;

public class FutureTaskByExecutor {
    private static ExecutorService executor = Executors.newFixedThreadPool(3);
    public static final int MAX_TURN = 5;
    public static final int COMPUTE_TIMES = 100000000;

    static class DemoThread implements Runnable {
        @Override
        public void run() {
            for (int j = 1; j < MAX_TURN; j++) {
                System.out.println(Thread.currentThread().getName() + ", 輪次" + j);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class ReturnableTask implements Callable<Long> {
        @Override
        public Long call() throws Exception {
            long startTime = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " 線程運行開始");
            for (int j = 1; j < MAX_TURN; j++) {
                System.out.println(Thread.currentThread().getName() + ", 輪次" + j);
                Thread.sleep(10);
            }
            long used = System.currentTimeMillis() - startTime;
            System.out.println(Thread.currentThread().getName() + "運行結束");
            return used;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        executor.execute(new DemoThread());

        executor.execute(() -> {
            for (int i = 1; i < MAX_TURN; i++) {
                System.out.println(Thread.currentThread().getName() + ", 輪次" + i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Future<Long> future = executor.submit(new ReturnableTask());
        Long result = future.get();

        System.out.println("異步任務執行結果為" + result);
    }
}
