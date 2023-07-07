package com.example.thread_demo.lock;

public class ObjectMethodLock {

    //當myLock被作為鎖搶走時,如果其他線程調用myLock的同步方法依然要等myLock被釋放

    public static void main(String[] args) throws InterruptedException {
        MyLock myLock = new MyLock();
        Service4 service4 = new Service4();

        Thread threadA = new Thread(() -> {
            try {
                service4.testMethod(myLock);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread threadB = new Thread(myLock::speedPrintString);

        threadA.setName("a");
        threadA.start();

        Thread.sleep(100);

        threadB.setName("b");
        threadB.start();

    }


}

class Service4{
    public void testMethod(MyLock myLock) throws InterruptedException {
        synchronized (myLock){
            System.out.println("testMethod getLock time = "+System.currentTimeMillis()+"threadName " + Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("testMethod releaseLock time = "+System.currentTimeMillis()+"threadName " + Thread.currentThread().getName());
        }
    }
}
