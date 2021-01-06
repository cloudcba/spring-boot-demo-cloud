package com.neo.test.treadtest;


/**
 * Created by Frank
 */
public class ThreadsDemo1 extends Thread {
    private String msg;
    private int count;

    public ThreadsDemo1(final String msg, int n) {
        this.msg = msg;
        count = n;
        setName(msg + " runner Thread");
    }

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

    public static void main(String[] args) {
        new ThreadsDemo1("Hello from X", 10).start();
        new ThreadsDemo1("Hello from Y", 15).start();
    }
}