package com.example.thread_demo.thread_local;

import com.google.gson.Gson;

import java.util.stream.Stream;

public class ThreadLocalDemo {

    public static ThreadLocal<String> tl = new ThreadLocal<>();
    public static void main(String[] args) {
        if(tl.get() == null){
            System.out.println("not set");
            tl.set("my value");
        }
        System.out.println(tl.get());
        System.out.println(tl.get());
    }
}
