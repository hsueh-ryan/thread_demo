package com.example.thread_demo.stop_thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 標籤中斷
 */
public class InterruptedFlag {
    public static List<String> list1 = new ArrayList<>();
    public static List<String> list2 = new ArrayList<>();

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    if (Thread.interrupted()) {
                        throw new InterruptedException("線程被中斷");
                    }
                    list1.add("data A");
                    System.out.println("list1 size=" + list1.size());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //因為interrupted會刷新標籤,所以list1中斷後換list2 add

            try {
                while (true) {
                    if (Thread.interrupted()) {
                        throw new InterruptedException("線程被中斷");
                    }
                    list2.add("data B");
                    System.out.println("list2 size=" + list2.size());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();

        boolean list1IsInterrupted = false;
        boolean list2IsInterrupted = false;

        while (thread.isAlive()) {
            if (list1.size() > 500 && !list1IsInterrupted) {
                thread.interrupt();
                list1IsInterrupted = true;
            }

            if (list2.size() > 500 && !list2IsInterrupted) {
                thread.interrupt();
                list2IsInterrupted = true;
            }
        }
    }
}
