package com.example.thread_demo.notify;

public class WaitTest {

    //因為沒有對象鎖,所以使用wait會發生異常
//    public static void main(String[] args) {
//        try {
//            String s = new String("");
//            s.wait();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }


    //使用對象鎖,所以可以執行wait

    public static void main(String[] args) {
        try {
            String lock = new String("");
            synchronized (lock){
                lock.wait();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
