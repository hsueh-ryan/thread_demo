package com.example.thread_demo.collection;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockQueueDemo {

    /*
        ArrayBlockingQueue如果隊列已滿put再放入元素則會阻塞,需等待有空餘空間時在繼續添加
        使用take取出元素,如對列沒有元素,則會呈阻塞狀態,等到對列裡有元素
     */
    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

        try {
            queue.put("a");
            queue.put("b");
            queue.put("c");
            System.out.println("size = " + queue.size());
            System.out.println(System.currentTimeMillis());
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
            queue.put("d");
            System.out.println(queue.take());
            System.out.println("size = "+ queue.size());
            System.out.println(queue.take());
            System.out.println(System.currentTimeMillis());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
