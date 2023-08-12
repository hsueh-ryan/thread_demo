package com.example.thread_demo.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenAcceptDemo {

    //兩個異步任務需要串行
    //第二個任務接收consume類別
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> 10).thenAccept(System.out::println);
        future.get();
    }
}
