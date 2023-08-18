package com.example.thread_demo.completable_future;

import java.util.concurrent.CompletableFuture;

public class AcceptEitherDemo {

    /**
     * 與applyToEither的區別是C任務無返回值
     * @param args
     * @throws InterruptedException
     */

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("task 1 thread name = " + Thread.currentThread().getName());
            return 10+10;
        }).acceptEither(CompletableFuture.supplyAsync(()->{
            System.out.println("task 2 thread name = " + Thread.currentThread().getName());
            return 3+3;
        }),x->{
            System.out.println("task 3 thread name = " + Thread.currentThread().getName());
            System.out.println("x");
        });

        Thread.sleep(3000);
    }

}
