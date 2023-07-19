package com.example.thread_demo.volatile_test;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    //i++除了可以用synchronized外,也可以使用AtomicInteger來達到原子性效果

    public static void main(String[] args) {
        TestThread testThread = new TestThread();

        Thread one = new Thread(testThread);
        one.start();

        Thread two = new Thread(testThread);
        two.start();

        Thread three = new Thread(testThread);
        three.start();

        Thread four = new Thread(testThread);
        four.start();

        Thread five = new Thread(testThread);
        five.start();
    }

}

class TestThread extends Thread{
    private AtomicInteger count = new AtomicInteger(0);
//    private int count =0;

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(count.incrementAndGet());
        }
    }


//    @Override
//    public void run() {
//        for (int i = 0; i < 10000; i++) {
//            System.out.println(count++);
//        }
//    }
}
