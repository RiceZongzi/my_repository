package com.rice.thread;

/**
 * @author wgz
 * @description
 * @date 2020/10/26 17:40
 */
public class PoolDemo implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "创建了一个新的线程执行");
    }
}
