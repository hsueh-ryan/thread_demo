package com.example.thread_demo.group;

public class ThreadGroupDemo {

    /*
        ThreadGroup 可以將thread 進行分組
     */

    public static void main(String[] args) {
        ThreadGroup myThreadGroup = new ThreadGroup("my thread group");

        Thread threadA = new Thread(myThreadGroup,() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("thread name = " + Thread.currentThread().getName());
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread threadB = new Thread(myThreadGroup,() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("thread name = " + Thread.currentThread().getName());
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        threadA.start();
        threadB.start();

        System.out.println("group thread count = " + myThreadGroup.activeCount());
        System.out.println("group name = " + myThreadGroup.getName());

    }
}
