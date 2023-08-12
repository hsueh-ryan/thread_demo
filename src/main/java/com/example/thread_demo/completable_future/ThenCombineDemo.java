package com.example.thread_demo.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenCombineDemo {

    //將兩個任務執行完成,再將兩個任務處理結果交給thenCombine操作
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "hello");
        CompletableFuture<String> result = future1.thenCombine(future2, (t, u) -> t + " " + u);
        System.out.println(result.get());
    }
}
