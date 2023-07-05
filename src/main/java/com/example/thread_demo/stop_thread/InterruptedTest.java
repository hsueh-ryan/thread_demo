package com.example.thread_demo.stop_thread;

public class InterruptedTest {

    public static void main1(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                System.out.println("i=" + (i + 1));
            }
        });
        thread.start();
        Thread.sleep(10);

        //標註停止標籤
        thread.interrupt();

        //測試當前線程是否中斷,當前線程為main,main並未中斷過.所以輸出結果為false
        //thread.interrupted()與Thread.interrupted()是一樣的
        System.out.println("是否停止 1 ? ="+thread.interrupted());
        System.out.println("是否停止 2 ? ="+thread.interrupted());
        System.out.println("end");
    }

    public static void main(String[] args) {
        Thread.currentThread().interrupt();
        //調用第一次返回true並且刷新標籤 所以2返回false
        System.out.println("是否停止 1 ? ="+Thread.interrupted());
        System.out.println("是否停止 2 ? ="+Thread.interrupted());

        System.out.println("end");
    }
}
