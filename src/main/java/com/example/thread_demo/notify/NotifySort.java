package com.example.thread_demo.notify;


/**
 * 可以看出notify喚醒的順序是依照wait的順序
 *
 *
 */

//TODO notifyAll應該會倒序喚醒,實測沒有
public class NotifySort {

    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();

        for (int i = 0; i < 10; i++) {
            Thread threadA = new Thread(myService::waitMethod);
            threadA.start();
        }

        Thread.sleep(1000);


//        Thread t1 = new Thread(myService::notifyMethod);
//        t1.start();
//        Thread.sleep(500);
//        Thread t2 = new Thread(myService::notifyMethod);
//        t2.start();
//        Thread.sleep(500);
//        Thread t3 = new Thread(myService::notifyMethod);
//        t3.start();
//        Thread.sleep(500);
//        Thread t4 = new Thread(myService::notifyMethod);
//        t4.start();
//        Thread.sleep(500);
//        Thread t5 = new Thread(myService::notifyMethod);
//        t5.start();
//        Thread.sleep(500);


        Thread t6 = new Thread(myService::notifyAllMethod);
        t6.start();


    }

}

class MyService{
     private Object lock = new Object();


     public void waitMethod(){
         try {
             synchronized (lock){
                 System.out.println("start wait time =" +System.currentTimeMillis() + " "+Thread.currentThread().getName());
                 lock.wait();
                 System.out.println("end wait time =" +System.currentTimeMillis() + " "+Thread.currentThread().getName());
             }

         }catch (InterruptedException e){
             e.printStackTrace();
         }
     }

    public void notifyMethod(){
            synchronized (lock){
                System.out.println("start notify time =" +System.currentTimeMillis() + " "+Thread.currentThread().getName());
                lock.notify();
                System.out.println("end notify time =" +System.currentTimeMillis() + " "+Thread.currentThread().getName());
            }
    }

    public void notifyAllMethod(){
        synchronized (lock){
            System.out.println("start notify time =" +System.currentTimeMillis() + " "+Thread.currentThread().getName());
            lock.notifyAll();
            System.out.println("end notify time =" +System.currentTimeMillis() + " "+Thread.currentThread().getName());
        }
    }
}
