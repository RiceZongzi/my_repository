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

    /**
     *     卖票案例出现了线程安全问题
     *     卖出了不存在的票和重复的票
     *
     *     解决线程安全问题的三种方案:使用Lock锁
     *     java.util.concurrent.locks.Lock接口
     *     Lock 实现提供了比使用 synchronized 方法和语句可获得的更广泛的锁定操作。
     *     Lock接口中的方法:
     *         void lock()获取锁。
     *         void unlock()  释放锁。
     *     java.util.concurrent.locks.ReentrantLock implements Lock接口
     *
     *
     *     使用步骤:
     *         1.在成员位置创建一个ReentrantLock对象
     *         2.在可能会出现安全问题的代码前调用Lock接口中的方法lock获取锁
     *         3.在可能会出现安全问题的代码后调用Lock接口中的方法unlock释放锁
     */
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
