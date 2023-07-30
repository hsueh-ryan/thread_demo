package com.example.thread_demo.notify;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class WhileWithoutNotify {


    public static void main(String[] args) {
        MyList myList = new MyList();
        ThreadA threadA = new ThreadA(myList);
        threadA.setName("A");
        threadA.start();
        ThreadB threadB = new ThreadB(myList);
        threadB.setName("B");
        threadB.start();
    }

}

class MyList{

    //使用volatile 實現ＡＢ線程之間的可視性
    private volatile List<String> list = new ArrayList<>();

    public void add(){
        list.add("test");
    }

    public int size(){
        return list.size();
    }
}

class ThreadA extends Thread{
    private final MyList myList;

    public ThreadA(MyList myList) {
        this.myList = myList;
    }

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            myList.add();
            System.out.println("添加了" + (i+1) + "個元素");
            Thread.sleep(1000);
        }
    }
}

class ThreadB extends Thread{
    private final MyList myList;

    public ThreadB(MyList myList) {
        this.myList = myList;
    }

    @Override
    public void run() {
        try {
            //雖然達到了線程之間的通信,但不停地通過while語句輪詢機制來檢測某一個條件,會浪費cup資源,輪詢的間隔很短會更浪費
            while (true){
                if(myList.size() == 5){
                    System.out.println("size=5, 線程 B 要退出了");
                    throw new InterruptedException();
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}


