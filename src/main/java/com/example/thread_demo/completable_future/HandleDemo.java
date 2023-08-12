package com.example.thread_demo.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class HandleDemo {

    /**
     * 可以接收到兩個參數,回傳結果與可拋出異常
     * 可以自訂義遇到異常或正確執行的處理結果
     *
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync thread" + Thread.currentThread().getName());
            int i = 10 / 0;  //測試異常處理
            return 10;
        }).handle((param, throwable) -> {
            System.out.println("handle thread" + Thread.currentThread().getName());
            int result = -1;
            if (throwable == null) {
                result = param * 2;
            } else {
                System.out.println(throwable.getMessage());
            }
            return result;
        });
        System.out.println(future.get());
    }
}
