package com.rice.thread;

/**
 * @author wgz
 * @description
 * @date 2020/10/26 10:59
 */
public class MyThreadByImplementsRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("This is " + Thread.currentThread().getName());
        for (int i = 0; i < 50; i++) {
            // getName()方法，继承自父类
            System.out.println(Thread.currentThread().getName() + i);
        }
    }
}
