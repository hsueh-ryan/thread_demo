package com.example.thread_demo.hold_lock;

public class HoldLockTest {
//查看當前執行緒是否獲得鎖對象
    public static void main(String[] args) {
        System.out.println("A " +Thread.holdsLock(HoldLockTest.class));
        synchronized (HoldLockTest.class){
            System.out.println("B " +Thread.holdsLock(HoldLockTest.class));
        }
        System.out.println("C " +Thread.holdsLock(HoldLockTest.class));
    }
}
