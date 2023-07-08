package com.example.thread_demo.change_lock;


//異動鎖物件
public class ChangeLockTest {

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        Thread threadA = new Thread(service::testMethod, "A");
        Thread threadB = new Thread(service::testMethod, "B");

        threadA.start();
        //因為sleep50毫秒,B取到的鎖已經是被異動過的456所以並不會與Ａ競爭鎖
        //如果沒有sleep豪秒,A與B會共同競爭鎖,此時再做修改依然是等待123這把鎖,但還是有機會出現同時begin情況,因為A線程將鎖改為456,B線程才執行run方法
        Thread.sleep(50);
        threadB.start();
    }
}
class Service{
    private String lock = "123";

    public void testMethod(){
        synchronized (lock){
            System.out.println("thread name = " + Thread.currentThread().getName() + "begin = " + System.currentTimeMillis());
            lock = "456";
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("thread name = " + Thread.currentThread().getName() + "end = " + System.currentTimeMillis());
        }
    }
}
