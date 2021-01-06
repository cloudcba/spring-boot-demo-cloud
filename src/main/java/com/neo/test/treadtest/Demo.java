package com.neo.test.treadtest;

public class Demo {
    public static class MyThread implements Runnable {
        @Override
        public void run() {
            System.out.println("MyThread");
            System.out.println("MyThread222");
        }
    }

    public static void main(String[] args) {

        new Thread(new MyThread()).start();

        // Java 8 函数式编程，可以省略MyThread类
        new Thread(() -> {
            System.out.println("Java 8 匿名内部类");
        }).start();
    }
}