package com.example.thread_demo.lock;

//出現異常 鎖釋放
public class ExceptionReleaseLock {

    public static void main(String[] args) throws InterruptedException {
        Service2 service2 = new Service2();

        Thread threadA = new Thread(service2::testMethod);
        Thread threadB = new Thread(service2::testMethod);
        //threadA獲取鎖後,發生異常,Thread才執行
        threadA.setName("a");
        threadA.start();

        Thread.sleep(500);

        threadB.setName("b");
        threadB.start();
    }

    static class Service2{
        synchronized public void testMethod(){
            if(Thread.currentThread().getName().equals("a")){
                System.out.println("ThreadName = "+Thread.currentThread().getName() + " run beginTime="+System.currentTimeMillis());
                int i =1;
                while (i==1){
                    if((""+Math.random()).substring(0,8).equals("0.123456")){
                        System.out.println("ThreadName = "+Thread.currentThread().getName() + " run exceptionTime="+System.currentTimeMillis());
                        Integer.parseInt("a");
                    }
                }
            }else {
                System.out.println("Thread B run Time=" + System.currentTimeMillis());
            }
        }
    }

}
