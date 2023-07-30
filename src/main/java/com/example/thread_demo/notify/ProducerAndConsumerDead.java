package com.example.thread_demo.notify;


/**
 * 因為notify可能發生生產者通知生產者或是消費者通知消費者,所以發生了假死
 * value但消費者沒被通知到,還在等著生產者通知,但生產者在等消費者消費通知
 */
public class ProducerAndConsumerDead {

    public static String value = "";
    public static final Object lock = new Object();

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            Producer2 producer = new Producer2(lock);
            producer.setName("producer" + (i + 1));
            producer.start();
        }

        for (int i = 0; i < 2; i++) {
            Consumer2 consumer = new Consumer2(lock);
            consumer.setName("consumer" + (i + 1));
            consumer.start();
        }


    }


}

class Producer2 extends Thread {
    private final Object lock;

    public Producer2(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (lock) {
//                    System.out.println("enter syc value is empty " + ProducerAndConsumerDead.value.equals("") +" "+ Thread.currentThread().getName());
                    while (!ProducerAndConsumerDead.value.equals("")) {
                        lock.wait();
                    }
                    String val = System.currentTimeMillis() + "_" + System.nanoTime();
                    System.out.println("set value = " + val);
                    ProducerAndConsumerDead.value = val;
                    lock.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class Consumer2 extends Thread {
    private final Object lock;

    public Consumer2(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (lock) {
                    while (ProducerAndConsumerDead.value.equals("")) {
                        lock.wait();
                    }
                    System.out.println("get value = " + ProducerAndConsumerDead.value);
                    ProducerAndConsumerDead.value = "";
                    lock.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
