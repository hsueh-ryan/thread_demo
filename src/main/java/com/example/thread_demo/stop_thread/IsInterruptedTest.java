package com.example.thread_demo.stop_thread;

public class IsInterruptedTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                System.out.println("i=" + (i + 1));
            }
        });
        thread.start();
        Thread.sleep(10);

        //標註停止標籤
        thread.interrupt();

        //因為不會清除標籤所以都為true
        System.out.println("是否停止 1 ? ="+thread.isInterrupted());
        System.out.println("是否停止 2 ? ="+thread.isInterrupted());
        System.out.println("end");
    }
}
