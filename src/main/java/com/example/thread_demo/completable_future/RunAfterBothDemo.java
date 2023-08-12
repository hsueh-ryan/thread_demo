package com.example.thread_demo.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RunAfterBothDemo {

    /**
     * 與ThenCombine的差別是C不會依賴AB任務的回傳結果
     * 且C任務也沒有回傳
     * 等待AB任務完成後執行
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
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println("task 2 thread name = " + Thread.currentThread().getName());
            return 3 + 3;
        }), () -> {
            System.out.println("task 3 thread name = " + Thread.currentThread().getName());
            System.out.println("任務三結束");
        });

        future.get();
    }
}
