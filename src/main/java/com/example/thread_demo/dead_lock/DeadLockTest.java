package com.example.thread_demo.dead_lock;

import lombok.Data;

public class DeadLockTest {
    //相互持有對方的鎖造成死鎖

    public static void main(String[] args) throws InterruptedException {
        DealThread dealThread = new DealThread();
        dealThread.setUsername("a");

        Thread thread1 = new Thread(dealThread);
        thread1.start();

        Thread.sleep(100);

        dealThread.setUsername("b");
        Thread thread2 = new Thread(dealThread);
        thread2.start();
    }
}

@Data
class DealThread implements Runnable{

    private String username;
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    @Override
    public void run() {
        if(username.equals("a")){
            synchronized (lock1){
                System.out.println("username = "+username);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2){
                    System.out.println("lock1->lock2");
                }
            }
        }

        if(username.equals("b")){
            synchronized (lock2){
                System.out.println("username = "+username);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock1){
                    System.out.println("lock2->lock1");
                }
            }
        }

    }
}
