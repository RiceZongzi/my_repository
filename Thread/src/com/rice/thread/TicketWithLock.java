package com.rice.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wgz
 * @description
 * @date 2020/10/26 14:33
 */
public class TicketWithLock implements Runnable {

    private int ticket = 100;

    Lock lock = new ReentrantLock();

    /**
     * 执行卖票操作
     */
    @Override
    public void run() {
        // 每个窗口卖票的操作
        // 窗口永远开启
        while (true) {
            lock.lock();
            // 有票可以卖
            if (ticket > 0) {
                try {
                    // 出票操作
                    // 使用sleep模拟一下出票时间
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto‐generated catch block
                    e.printStackTrace();
                }
                // 获取当前线程对象的名字
                String name = Thread.currentThread().getName();
                System.out.println(name + "正在卖:" + ticket--);
            }
            lock.unlock();
        }
    }
}
