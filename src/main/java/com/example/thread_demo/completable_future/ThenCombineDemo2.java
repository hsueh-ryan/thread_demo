package com.example.thread_demo.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenCombineDemo2 {

    /**
     * 某個任務同時依賴兩個異步任務的執行結果
     * AB任務都完成後會執行C任務
     * C任務有可能用A或B任務的線程執行
     *
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("task 1 thread name = " + Thread.currentThread().getName());
            return 10 + 10;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println("task 2 thread name = " + Thread.currentThread().getName());
            return 3 + 3;
        }), (x, y) -> {
            System.out.println("task 3 thread name = " + Thread.currentThread().getName());
            return x + y;
        });
        System.out.println(future.get());

    }
}
