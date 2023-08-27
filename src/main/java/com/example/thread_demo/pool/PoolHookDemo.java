package com.example.thread_demo.pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PoolHookDemo {

    /*
        ThreadPoolExecutor線程調度器為每個任務提供三個鉤子方法重寫
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(3)) {
            private final ThreadLocal<Long> startTime = new ThreadLocal<>();

            @Override
            protected void terminated() {
                System.out.println("調度器已終止");
            }

            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println(t + "鉤子被執行");
                startTime.set(System.currentTimeMillis());
            }

            @Override
            protected void afterExecute(Runnable target, Throwable t) {
                super.afterExecute(target, t);
                long time =( System.currentTimeMillis()) - startTime.get();
                System.out.println(target + "鉤子被執行, 任務執行時長 : " + time);
                startTime.remove();
            }
        };

//        for (int i = 0; i <= 5; i++) {
//            pool.execute(new Thread());
//        }

        pool.execute(new Thread(()->{
            System.out.println("run start");
            System.out.println("thread sleep...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }));

        Thread.sleep(5000);

        pool.shutdown();


    }
}
