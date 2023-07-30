package com.example.thread_demo.notify;

import java.util.ArrayList;
import java.util.List;

public class WhileWithWaitAndNotify {

    public static void main(String[] args) throws InterruptedException {

        MyList2 lock = new MyList2();

        Thread threadA = new Thread(() -> {
            try {
                synchronized (lock) {
                    if (lock.size() != 5) {
                        System.out.println("start wait time = " + System.currentTimeMillis());
                        lock.wait();
                        System.out.println("end wait time = " + System.currentTimeMillis());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                synchronized (lock) {
                    for (int i = 0; i < 10; i++) {
                        lock.add();
                        if (lock.size() == 5) {
                            //不會立即釋放鎖,會等到線程離開同步代碼塊才會釋放
                            lock.notify();
                            System.out.println("send notify");
                        }
                        System.out.println("add " + (i + 1) + "element");
                        Thread.sleep(1000);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        Thread.sleep(50);
        threadB.start();

    }
}

class MyList2 {
    private List<String> list = new ArrayList<>();

    public void add() {
        list.add("test");
    }

    public int size() {
        return list.size();
    }
}
