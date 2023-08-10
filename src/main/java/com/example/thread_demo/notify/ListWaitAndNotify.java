package com.example.thread_demo.notify;

import java.util.ArrayList;
import java.util.List;

public class ListWaitAndNotify {


    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        Thread pushThread = new Thread(() -> {
            while (true) {
                myStack.push();
            }
        });

        Thread popThread = new Thread(() -> {
            while (true) {
                myStack.pop();
            }
        });

        popThread.start();
        pushThread.start();
    }
}

class MyStack{

    //因為synchronized為this所以必須使用this來做notify與wait操作
    private final List<String> list= new ArrayList<>();

    synchronized public void push(){

             try {
                 if(list.size() == 1){
                     this.wait();
                 }
                 list.add("any String = " + Math.random());
                 this.notify();
                 System.out.println("push = "+ list.size());
             }catch (InterruptedException e){
                 e.printStackTrace();
             }


    }


    synchronized public String pop(){
             String returnValue = "";
             try {
                 if(list.size() == 0){
                     this.wait();
                 }
                 returnValue = list.get(0);
                 list.remove(0);
                 this.notify();
                 System.out.println("pop=" +list.size());
             }catch (InterruptedException e){
                 e.printStackTrace();
             }
             return returnValue;


    }
}
