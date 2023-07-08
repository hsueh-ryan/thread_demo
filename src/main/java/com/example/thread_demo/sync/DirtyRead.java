package com.example.thread_demo.sync;

public class DirtyRead {

    public static void main(String[] args) throws InterruptedException {
        PublicVar publicVar = new PublicVar();
        Thread thread = new Thread(() -> {
            publicVar.setValue("B", "BB");
        });

        //

        thread.start();

        Thread.sleep(200);

        publicVar.getValue();

    }
}

class PublicVar {
    public String username = "A";
    public String password = "AA";

    synchronized public void setValue(String username, String password) {
        try {
            this.username = username;
            Thread.sleep(5000);
            this.password = password;

            System.out.println("setValue method thread name=" + Thread.currentThread().getName() + " username=" + username + "password=" + password);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //因為getValue沒上鎖,導致獲取到髒資料
    public void getValue() {
        System.out.println("getValue method thread name=" + Thread.currentThread().getName() + " username=" + username + "password=" + password);
    }
}
