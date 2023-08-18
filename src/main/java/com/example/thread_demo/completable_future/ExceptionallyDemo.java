package com.example.thread_demo.completable_future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ExceptionallyDemo {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(new Random().nextInt()%2>=0) {
                int i = 12/0;
            }
            System.out.println("run end ...");
        });

        future.whenComplete((t, action) -> System.out.println("執行完成！"));
        future.exceptionally(t -> {
            System.out.println("執行失敗！"+t.getMessage());
            return null;
        });

        TimeUnit.SECONDS.sleep(2);
    }
}
