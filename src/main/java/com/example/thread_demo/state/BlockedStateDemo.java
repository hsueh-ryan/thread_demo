package com.example.thread_demo.state;

public class BlockedStateDemo {

    /*
        thread 等待鎖的時候狀態為BLOCKED

        thread name = A enter syc method
        main call thread b state = BLOCKED
        thread name = B enter syc method

     */

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        Thread threadA = new Thread(service::sycMethod);
        threadA.setName("A");
        threadA.start();

        Thread.sleep(1000);

        Thread threadB = new Thread(service::sycMethod);
        threadB.setName("B");
        threadB.start();

        Thread.sleep(1000);

        System.out.println("main call thread b state = " + threadB.getState());


    }

    static class Service {
        synchronized public void sycMethod(){
            System.out.println("thread name = "+Thread.currentThread().getName() + " enter syc method");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
