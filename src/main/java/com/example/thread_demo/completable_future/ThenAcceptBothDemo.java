package com.example.thread_demo.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenAcceptBothDemo {

    /**
     * 與runAfterBoth的差別是C任務可以接收到AB任務的結果,本身則沒有輸出值
     *
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("task 1 thread name = " + Thread.currentThread().getName());
            return 10 + 10;
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println("task 2 thread name = " + Thread.currentThread().getName());
            return 3 + 3;
        }), (x, y) -> {
            System.out.println("task 3 thread name = " + Thread.currentThread().getName());
            System.out.println("任務三結束 result = " + (x + y));
        });

        future.get();
    }
}
