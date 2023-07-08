package com.example.thread_demo.sync;

public class SyncTest {

    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        Thread threadA = new Thread(myObject::methodA);
        threadA.setName("A");
        Thread threadB = new Thread(myObject::methodB);
        threadB.setName("B");
        //雖然線程A持有了object對像鎖,但線程B依然可以異步調用非synchronized的methodB方法
        //如果在methodB加上synchronized就無法異步調用
        threadA.start();
        threadB.start();
    }
}

class MyObject {
    synchronized public void methodA() {
        try {
            System.out.println("begin methodA threadName=" + Thread.currentThread().getName() + "begin time" + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("end methodA endTime=" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void methodB() {
        try {
            System.out.println("begin methodB threadName=" + Thread.currentThread().getName() + "begin time" + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("end methodB endTime=" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
