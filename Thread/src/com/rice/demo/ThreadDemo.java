package com.rice.demo;

import com.rice.thread.MyThreadByExtendsThread;
import com.rice.thread.MyThreadByImplementsRunnable;

/**
 * @author wgz
 * @description
 * @date 2020/10/23 16:33
 */
public class ThreadDemo {

    public static void main(String[] args) {
//        method1();
        method2();
    }

    private static void method1() {
        MyThreadByExtendsThread t = new MyThreadByExtendsThread("tThread");
        t.start();
        System.out.println("This is main");
        for (int i = 0; i < 50; i++) {
            System.out.println("main" + i);
        }
    }

    private static void method2() {
        MyThreadByImplementsRunnable r = new MyThreadByImplementsRunnable();
        Thread thread = new Thread(r, "tRunnable");
        thread.start();
        System.out.println("This is main");
        for (int i = 0; i < 50; i++) {
            System.out.println("main" + i);
        }
    }
}
