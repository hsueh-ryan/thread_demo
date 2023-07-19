package com.example.thread_demo.volatile_test;

import lombok.Data;

public class VolatileTest {

    public static void main(String[] args) throws InterruptedException {
        RunThread runThread = new RunThread();
        runThread.start();
        Thread.sleep(1000);
        runThread.setRun(false);
        System.out.println("已將 runThread 賦值為 false");
    }
}

@Data
class RunThread extends Thread {

    /**
     * 公共內存與私有內存都會保存一份isRun
     * 線程運行後在線程的私有內存中取得isRun的值為true
     * 接下來主線程將isRun改為false
     * 卻是將公共內存中的isRun改為false
     *
     * 可以使用volatile解決
     * volatile主要作用就是當線程訪問isRun變量時
     * 強制從公共內存中取值
     */
    private boolean isRun = true;

    @Override
    public void run() {
        System.out.println("run start");
        while (isRun == true) {

        }
        System.out.println("run end");
    }
}