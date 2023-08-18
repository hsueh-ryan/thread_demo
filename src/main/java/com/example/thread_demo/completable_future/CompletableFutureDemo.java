package com.example.thread_demo.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureDemo {

    private static ExecutorService executor = Executors.newFixedThreadPool(3);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(Thread.currentThread().getName() + "拋出異常");
            throw new RuntimeException("發生異常");
        });



        future.whenComplete((unused, throwable) ->
                System.out.println(Thread.currentThread().getName() + "執行完成"));

        CompletableFuture<Void> exceptionally = future.exceptionally(x -> {
            System.out.println(Thread.currentThread().getName() + "執行失敗");
            System.out.println(x.getMessage());
            return null;
        });

        Thread.sleep(5000);
//        future.get();
//        Thread.sleep(5000);
//        System.out.println("finish");
//        exceptionally.get();
    }
}
