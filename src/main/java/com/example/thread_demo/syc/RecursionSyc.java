package com.example.thread_demo.syc;

/**
 * 重入鎖
 */
public class RecursionSyc {

    public static void main(String[] args) {
        Service service = new Service();
        Thread thread = new Thread(service::service1);
        thread.start();
    }
}

class Service{

    //重入鎖指自己可以再次獲取自己的內部鎖,如果沒有重入鎖功能service1調用service2就無法調用
    synchronized public void service1(){
        System.out.println("service1");
        service2();
    }

    synchronized public void service2(){
        System.out.println("service2");
        service3();
    }

    synchronized public void service3(){
        System.out.println("service3");
    }
}
