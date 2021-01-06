package com.neo.test.treadtest;



/**
 * Created by Frank
 */
public class ThreadsDemo2 implements Runnable {
    private String msg;
    private Thread t;
    private int count;

    public static void main(String[] args) {
        new ThreadsDemo2("Hello from X", 10);
        new ThreadsDemo2("Hello from Y", 15);
    }

    public ThreadsDemo2(String m, int n) {
        this.msg = m;
        count = n;
        t = new Thread(this);
        t.setName(msg + "runner Thread");
        t.start();
    }

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
}