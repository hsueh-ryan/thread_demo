package com.example.thread_demo.map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {

    public static void main(String[] args) throws InterruptedException {
        /**
         * 如果使用HashMap為線程不安全,會有數據遺漏問題
         * ConcurrentHashMap 與 Hashtable 都支持併發put操作
         */
//         HashMap<String, String> map = new HashMap<>();
//        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        Hashtable<String, String> map = new Hashtable<>();


        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                map.put(Thread.currentThread().getName() + " " + (i + 1), Thread.currentThread().getName() + " " + (i + 1));
                System.out.println(Thread.currentThread().getName() + " " + (i + 1));
            }
        });
        threadA.setName("A");

        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                map.put(Thread.currentThread().getName() + " " + (i + 1), Thread.currentThread().getName() + " " + (i + 1));
                System.out.println(Thread.currentThread().getName() + " " + (i + 1));
            }
        });
        threadB.setName("B");

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        System.out.println(map.size());
    }
}
