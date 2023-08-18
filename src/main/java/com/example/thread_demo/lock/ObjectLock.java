package com.example.thread_demo.lock;

public class ObjectLock {

    //使用其他物件當成鎖的優點：同一class不同方法可以透過不同的物件鎖來區分
    //鎖必須是同個物件否則仍會異步調用

    public static void main(String[] args) {
        Service3 service3 = new Service3();
        Thread threadA = new Thread(() -> {
            try {
                service3.setUsernamePassword("a", "aa");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                service3.setUsernamePassword("b", "bb");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        threadA.setName("A");
        threadA.start();

        threadB.setName("B");
        threadB.start();
    }

    static class Service3{
        private String username;
        private String password;

        //可以透過其他物件當成鎖,不一定要透過this物件
        private String anyString = new String();

        public void setUsernamePassword(String username,String password) throws InterruptedException {
            synchronized (anyString){
                System.out.println("ThreadName" + Thread.currentThread().getName()+System.currentTimeMillis()+"enter");
                Thread.sleep(3000);
                this.username = username;
                this.password = password;
                System.out.println("ThreadName" + Thread.currentThread().getName()+System.currentTimeMillis()+"exit");
            }
        }
    }
}



