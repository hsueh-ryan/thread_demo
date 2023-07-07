package com.example.thread_demo.extend_syc;

//繼承環境的重入鎖
public class ExtendRecursionSyc {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            Child child = new Child();
            //子類別可以透過重入鎖調用父類別方法
            child.childMethod();
        });
        thread.start();
    }
}
