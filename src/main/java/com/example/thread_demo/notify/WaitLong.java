package com.example.thread_demo.notify;


/**
 * 雖然wait加上時間參數，時間到後會喚醒
 * 但還是必須獲得鎖,B線程將鎖佔用,所以喚醒時間皆為b線程釋放鎖的時間
 * 打印時間差別為8秒而不是5秒
 */
public class WaitLong {

    public static void main(String[] args) throws InterruptedException {
        MyService2 myService2 = new MyService2();

        for (int i = 0; i < 10; i++) {
            new Thread(myService2::testMethod).start();
        }
        Thread.sleep(100);

//        new Thread(myService2::longTimeSyn).start();
    }
    
    
}
class MyService2{
    
    public void testMethod(){
        try {
            synchronized (this){
                System.out.println("start wait "+ System.currentTimeMillis() +" "+Thread.currentThread().getName());
                wait(5000);
                System.out.println("end wait "+ System.currentTimeMillis() +" "+Thread.currentThread().getName());
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    synchronized public void longTimeSyn(){
        try {
            Thread.sleep(8000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
