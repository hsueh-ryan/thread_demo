package com.example.thread_demo.volatile_test;

public class AtomTest {

    //打印結果並非每次都為10000說明volatile無原子性
    //加上synchronized可以達到原子性效果也不需要加上volatile,synchronized本身就有可見性
    public static void main(String[] args) throws InterruptedException {
        ThreadOne[] threadOnes = new ThreadOne[100];
        for (int i = 0; i < 100; i++) {
            threadOnes[i] = new ThreadOne();
        }

        for (int i = 0; i < 100; i++) {
            threadOnes[i].start();
        }
    }
}

