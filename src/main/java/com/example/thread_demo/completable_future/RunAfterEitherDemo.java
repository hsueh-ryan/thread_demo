package com.example.thread_demo.completable_future;

import java.util.concurrent.CompletableFuture;

public class RunAfterEitherDemo {

    /**
     * 與applyToEither差別是C方法沒有返回值且不接收AB任務結果
     * @param args
     * @throws InterruptedException
     */

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("task 1 thread name = " + Thread.currentThread().getName());
            return 10+10;
        }).runAfterEither(CompletableFuture.supplyAsync(()->{
            System.out.println("task 2 thread name = " + Thread.currentThread().getName());
            return 3+3;
        }),()->{
            System.out.println("task 3 thread name = " + Thread.currentThread().getName());
        });

        Thread.sleep(3000);
    }
}
