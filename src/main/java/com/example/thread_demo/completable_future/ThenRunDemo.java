package com.example.thread_demo.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenRunDemo {

    //不關心上一個任務處理結果,只要上一個任務執行完成,就開始執行thenRun
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> 10).thenRun(() -> {
            System.out.println("thenRun ...");
        });
        future.get();
    }
}
