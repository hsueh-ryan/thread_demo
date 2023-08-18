package com.example.thread_demo.notify;

public class CrossBackup {

    public static void main(String[] args) {
        DBTools dbTools = new DBTools();
        for (int i = 0; i < 20; i++) {
            Thread threadB = new Thread(dbTools::backupB);
            threadB.start();
            Thread threadA = new Thread(dbTools::backupA);
            threadA.start();
        }
    }


    static class DBTools {
        volatile boolean prevIsA = false;

        synchronized public void backupA() {
            try {
                while (prevIsA) {
                    wait();
                }
                for (int i = 0; i < 5; i++) {
                    System.out.println("AAAAA");
                }
                prevIsA = true;
                notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized public void backupB() {
            try {
                while (!prevIsA) {
                    wait();
                }
                for (int i = 0; i < 5; i++) {
                    System.out.println("BBBBB");
                }
                prevIsA = false;
                notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
