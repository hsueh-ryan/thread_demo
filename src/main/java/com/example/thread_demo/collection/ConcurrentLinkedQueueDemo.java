package com.example.thread_demo.collection;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
//        Queue<String> queue = new LinkedList<>();

        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                queue.add("Thread A " + (i + 1));
            }
        });

        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                queue.add("Thread B " + (i + 1));
            }
        });

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        System.out.println(queue.size());
    }



}
