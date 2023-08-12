package com.example.thread_demo.completable_future;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThenComposeDemo2 {

    public static void main(String[] args) {
        CompletableFuture<List<String>> total = CompletableFuture.supplyAsync(() -> {
            List<String> stuff = new ArrayList<>();
            stuff.add("A");
            stuff.add("B");
            return stuff;
        }).thenCompose(list -> {
            CompletableFuture<List<String>> insideFuture = CompletableFuture.supplyAsync(() -> {
                List<String> stuff = new ArrayList<>();
                stuff.add("C");
                stuff.add("D");
                List<String> allStuff = Stream.of(list, stuff).flatMap(Collection::stream).collect(Collectors.toList());
                return allStuff;
            });
            return insideFuture;
        });
        System.out.println(total.join().size());
    }
}
