package com.example.thread_demo.collection;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {

    /*
        使用ArrayList為非線程安全
        可以使用CopyOnWriteArrayList來解決非執行緒安全問題
     */

    public static void main(String[] args) throws JsonProcessingException, InterruptedException {
//        List<String> list = new ArrayList<>();

        List<String> list = new CopyOnWriteArrayList<>();

        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                list.add("");
            }
        });

        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                list.add("");
            }
        });

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();
        System.out.println(list.size());
    }
}
