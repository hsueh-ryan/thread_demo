package com.example.thread_demo.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenApplyDemo {

    //兩個異步任務需要串行
    //第二個任務接收Function類別
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync thread" + Thread.currentThread().getName());
            return 10;
            //thenApply的參數為第一個任務的返回值,所以會依賴第一個任務的結果
        }).thenApply(x -> {
            System.out.println("then apply thread" + Thread.currentThread().getName());
            return x * 2;
        });

        System.out.println(future.get());
    }
}
