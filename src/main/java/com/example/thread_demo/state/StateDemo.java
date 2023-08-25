package com.example.thread_demo.state;

public class StateDemo {

    /*
        thread的創建到運行狀態變化
        construct method Thead.currentThread state = RUNNABLE
        construct method this.getStage state = NEW
        main call thread state 1 = NEW
        run method state = RUNNABLE
        main call thread state 2 = TERMINATED
     */

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        System.out.println("main call thread state 1 = " + myThread.getState());
        Thread.sleep(1000);
        myThread.start();
        Thread.sleep(1000);
        System.out.println("main call thread state 2 = " + myThread.getState());

    }


    static class MyThread extends Thread {
        public MyThread() {
            //此處打印的狀態為main線程的狀態,由main線程建構
            System.out.println("construct method Thead.currentThread state = " + Thread.currentThread().getState());
            System.out.println("construct method this.getStage state = " + this.getState());
        }

        @Override
        public void run() {
            System.out.println("run method state = " + Thread.currentThread().getState());
        }
    }
}
