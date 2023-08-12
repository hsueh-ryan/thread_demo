package com.example.thread_demo.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AllOfDemo {

    /**
     * 合併所有任務，等待所有任務結束後執行
     *
     * @param args
     */


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() ->
                {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("模擬任務1");
                }
        );
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> System.out.println("模擬任務2"));
        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> System.out.println("模擬任務3"));
        CompletableFuture<Void> future4 = CompletableFuture.runAsync(() -> System.out.println("模擬任務4"));

        CompletableFuture.allOf(future1, future2, future3, future4).thenRun(() -> {
            System.out.println("finish");
        }).get();
    }
}
