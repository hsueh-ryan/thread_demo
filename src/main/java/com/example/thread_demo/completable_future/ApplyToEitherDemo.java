package com.example.thread_demo.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ApplyToEitherDemo {

    /**
     * AB任務看誰先執行完,先執行完的就會使用該任務返回值傳入C任務
     * 若先執行完的任務發生異常則不會執行C任務
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("task 1 thread name = " + Thread.currentThread().getName());
            return 10+10;
        }).applyToEither(CompletableFuture.supplyAsync(()->{
            System.out.println("task 2 thread name = " + Thread.currentThread().getName());
            if (true){
                throw new RuntimeException("");
            }
            return 3+3;
        }),x->{
            System.out.println("task 3 thread name = " + Thread.currentThread().getName());
            System.out.println(x);
            return x;
        }).get();
        //因為applyToEither是AB任務看哪個任務執行完就執行C任務
        //所以get會取到C任務結果,由於main線程已執行完畢,而A任務的線程為守護線則就不會執行了

//        Thread.sleep(3000);

    }
}
