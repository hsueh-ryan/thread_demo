package com.example.thread_demo.notify;

/**
 * 如果生產者多會發生連續生產,導致value被覆蓋
 * 消費者多,增加連續消費""空值機率
 *
 * if (!ProducerAndConsumer.value.equals("")) {
 *                         lock.wait();
 *                     }
 * 將if改成while可以避免連續消費或連續生產,但還是會發生假死狀態
 * 因為notify可能發生生產者通知生產者或是消費者通知消費者
 */
public class ProducerAndConsumer {

    public static String value = "";
    public static final Object lock = new Object();

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            Producer producer = new Producer(lock);
            producer.setName("producer" + (i + 1));
            producer.start();
        }

        for (int i = 0; i < 1; i++) {
            Consumer consumer = new Consumer(lock);
            consumer.setName("consumer" + (i + 1));
            consumer.start();
        }


    }
}

class Producer extends Thread {
    private final Object lock;

    public Producer(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (lock) {
                    System.out.println("enter syc value is empty " + ProducerAndConsumer.value.equals("") +" "+ Thread.currentThread().getName());
                    if (!ProducerAndConsumer.value.equals("")) {
                        lock.wait();
                    }
                    String val = System.currentTimeMillis() + "_" + System.nanoTime();
                    System.out.println("set value = " + val);
                    ProducerAndConsumer.value = val;
                    lock.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class Consumer extends Thread {
    private final Object lock;

    public Consumer(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (lock) {
                    if (ProducerAndConsumer.value.equals("")) {
                        lock.wait();
                    }
                    System.out.println("get value = " + ProducerAndConsumer.value);
                    ProducerAndConsumer.value = "";
                    lock.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
