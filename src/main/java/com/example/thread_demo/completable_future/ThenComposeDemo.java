package com.example.thread_demo.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Supplier;

public class ThenComposeDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> f = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = 100;
                System.out.println("t1=" + t);
                return t;
            }

            //TODO 使用場景？
        }).thenCompose(new Function<Integer, CompletionStage<Integer>>() {
            @Override
            public CompletionStage<Integer> apply(Integer param) {
                return CompletableFuture.supplyAsync(new Supplier<Integer>() {
                    @Override
                    public Integer get() {
                        int t = param * 2;
                        System.out.println("t2=" + t);
                        return t;
                    }
                });
            }

        });
        System.out.println("thenCompose result : " + f.get());
    }
}
