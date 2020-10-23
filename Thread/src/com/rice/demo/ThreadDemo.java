package com.rice.demo;

import com.rice.thread.MyThreadByExtendsThread;

/**
 * @author wgz
 * @description
 * @date 2020/10/23 16:33
 */
public class ThreadDemo {

    public static void main(String[] args) {
        method1();
    }

    private static void method1() {
        MyThreadByExtendsThread t = new MyThreadByExtendsThread("tThread");
        t.start();
        System.out.println("This is main");
        for (int i = 0; i < 50; i++) {
            System.out.println("main" + i);
        }
    }
}
