package com.example.thread_demo.sync;


/**
 * 線程AB與C為異步關係,線程A與B是同步關係
 * AB為靜態方法所以是將Service5的class作為鎖
 * B為實體方法,所以是將Service5實體對象作為鎖
 *
 * 所以如果生成多個實體調用靜態方法彼此仍屬於競爭關係,因為class作為對象鎖只會有一把
 */
public class StaticLockTest {

    public static void main(String[] args) {
        Service5 service5 = new Service5();
        Thread threadA = new Thread(() -> {
            service5.printA();
        });

        Thread threadB = new Thread(() -> {
            service5.printB();
        });

        Thread threadC = new Thread(service5::printC);

        threadA.setName("A");
        threadA.start();

        threadB.setName("B");
        threadB.start();

        threadC.setName("C");
        threadC.start();


    }
}

class Service5 {

    synchronized public static void printA() {
        System.out.println("thread name " + Thread.currentThread().getName() + "enter printA time is " + System.currentTimeMillis());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("thread name " + Thread.currentThread().getName() + "exit printA time is " + System.currentTimeMillis());
    }

    synchronized public static void printB() {
        System.out.println("thread name " + Thread.currentThread().getName() + "enter printB time is " + System.currentTimeMillis());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("thread name " + Thread.currentThread().getName() + "exit printB time is " + System.currentTimeMillis());
    }

    synchronized public void printC() {
        System.out.println("thread name " + Thread.currentThread().getName() + "enter printC time is " + System.currentTimeMillis());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("thread name " + Thread.currentThread().getName() + "exit printC time is " + System.currentTimeMillis());
    }

}
