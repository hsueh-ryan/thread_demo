package com.example.thread_demo.thread_local;

public class InitValueDemo {

    /*
    可以透過繼承ThreadLocal來改寫initialValue改變預設為null的情況
     */
    public static ThreadLocalExt tle = new ThreadLocalExt();

    public static void main(String[] args) {
        if (tle.get() == null) {
            System.out.println("init value is null");
            tle.set("new value");
        }
        System.out.println(tle.get());
        System.out.println(tle.get());
    }
    static class ThreadLocalExt extends ThreadLocal<String>{
        @Override
        protected String initialValue() {
            return "I am default";
        }
    }
}
