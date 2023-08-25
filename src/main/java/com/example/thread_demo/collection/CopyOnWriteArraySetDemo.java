package com.example.thread_demo.collection;

import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWriteArraySetDemo {

    /*
        HashSet為非線程安全
        可以使用CopyOnWriteArraySet來解決
     */


    public static void main(String[] args) throws InterruptedException {
//        Set<String> set = new HashSet<>();

        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();

        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                set.add("A" + i);
            }
        });

        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                set.add("B" + i);
            }
        });

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();
        System.out.println(set.size());
    }
}
