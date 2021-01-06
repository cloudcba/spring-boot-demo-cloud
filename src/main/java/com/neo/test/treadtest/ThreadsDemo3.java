package com.neo.test.treadtest;

/**
 * Created by Frank
 */
public class ThreadsDemo3 {
    private int count;

    public static void main(String[] args) {
        new ThreadsDemo3("Hello from X", 10);
        new ThreadsDemo3("Hello from Y", 15);
    }
    public ThreadsDemo3(final String msg, int n) {
        this.count = n;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (count-- > 0) {
                    System.out.println(msg);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                System.out.println(msg + " all done.");
            }
        });
        t.setName(msg + " runner Thread");
        t.start();
    }
}