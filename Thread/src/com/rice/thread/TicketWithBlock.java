package com.rice.thread;

/**
 * @author wgz
 * @description
 * @date 2020/10/26 14:33
 */
public class TicketWithBlock implements Runnable {

    private int ticket = 100;


    /**
     * 卖票案例出现了线程安全问题
     *     卖出了不存在的票和重复的票
     *
     *     解决线程安全问题的一种方案:使用同步代码块
     *     格式:
     *         synchronized(锁对象){
     *             可能会出现线程安全问题的代码(访问了共享数据的代码)
     *         }
     *
     *     注意:
     *         1.通过代码块中的锁对象,可以使用任意的对象
     *         2.但是必须保证多个线程使用的锁对象是同一个
     *         3.锁对象作用:
     *             把同步代码块锁住,只让一个线程在同步代码块中执行
     */
    Object lock = new Object();

    /**
     * 执行卖票操作
     */
    @Override
    public void run() {
        // 每个窗口卖票的操作
        // 窗口永远开启
        while (true) {
            synchronized (lock) {
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
            }
        }
    }
}
