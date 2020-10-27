package com.rice.lambda;

/**
 * @author wgz
 * @description
 * @date 2020/10/27 11:03
 */
public class RunnableExample implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Runnable interface 线程创建了");
    }
}
