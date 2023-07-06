package com.example.thread_demo.daemon_thread;

public class DaemonThread {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (true) {
                try {
                    i++;
                    System.out.println("i=" + i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //將執行緒設定為守護線程,進程中如果沒有非守護線程,則守護線程會被銷毀
        //必須在調用start前設定為守護線程,否則會拋異常
        thread.setDaemon(true);
        thread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("main thread結束 daemon不應該在打印");
    }
}
