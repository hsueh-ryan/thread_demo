package com.example.thread_demo.state;

public class WaitingStateDemo {

    /*
        查看sleep中的thread狀態

        begin sleep
        main call thread state = TIMED_WAITING
        end sleep
     */

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("begin sleep");
                Thread.sleep(10000);
                System.out.println("end sleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();

        Thread.sleep(1000);

        System.out.println("main call thread state = " + thread.getState());
    }
}
