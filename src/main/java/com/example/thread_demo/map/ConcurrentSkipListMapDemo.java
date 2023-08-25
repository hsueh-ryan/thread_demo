package com.example.thread_demo.map;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class ConcurrentSkipListMapDemo {
    /*
        ConcurrentSkipListMap為有序且執行緒安全
     */

    public static void main(String[] args) throws InterruptedException {
        ConcurrentSkipListMap<String, String> map = new ConcurrentSkipListMap<>();

        map.put("C", "value_c");
        map.put("A", "value_a");
        map.put("B", "value_b");
        map.put("G", "value_g");
        map.put("Z", "value_z");
        map.put("S", "value_s");

        new Thread(() -> {
            Map.Entry<String, String> entry = map.pollFirstEntry();
            System.out.println("map size" + map.size());
            System.out.println("key  = " + entry.getKey() + "   " + "value = " + entry.getValue());
        }).start();

        Thread.sleep(1000);

        new Thread(() -> {
            Map.Entry<String, String> entry = map.pollFirstEntry();
            System.out.println("map size" + map.size());
            System.out.println("key  = " + entry.getKey() + "   " + "value = " + entry.getValue());
        }).start();

        Thread.sleep(1000);

        new Thread(() -> {
            Map.Entry<String, String> entry = map.pollFirstEntry();
            System.out.println("map size" + map.size());
            System.out.println("key  = " + entry.getKey() + "   " + "value = " + entry.getValue());
        }).start();

        Thread.sleep(1000);

        new Thread(() -> {
            Map.Entry<String, String> entry = map.pollFirstEntry();
            System.out.println("map size" + map.size());
            System.out.println("key  = " + entry.getKey() + "   " + "value = " + entry.getValue());
        }).start();

        Thread.sleep(1000);

        new Thread(() -> {
            Map.Entry<String, String> entry = map.pollFirstEntry();
            System.out.println("map size" + map.size());
            System.out.println("key  = " + entry.getKey() + "   " + "value = " + entry.getValue());
        }).start();

        Thread.sleep(1000);

        new Thread(() -> {
            Map.Entry<String, String> entry = map.pollFirstEntry();
            System.out.println("map size" + map.size());
            System.out.println("key  = " + entry.getKey() + "   " + "value = " + entry.getValue());
        }).start();


    }
}
