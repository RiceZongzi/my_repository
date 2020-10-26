package com.rice.thread;

/**
 * @author wgz
 * @description
 * @date 2020/10/26 14:33
 */
public class TicketWithMethod implements Runnable {

    private int ticket = 100;

    /**
     * 执行卖票操作
     */
    @Override
    public void run() {
        // 每个窗口卖票的操作
        // 窗口永远开启
        while (true) {
            sellTicket();
        }
    }

    /**
     * 卖票案例出现了线程安全问题
     *     卖出了不存在的票和重复的票
     *
     *     解决线程安全问题的二种方案:使用同步方法
     *     使用步骤:
     *         1.把访问了共享数据的代码抽取出来,放到一个方法中
     *         2.在方法上添加synchronized修饰符
     *
     *     格式:定义方法的格式
     *     修饰符 synchronized 返回值类型 方法名(参数列表){
     *         可能会出现线程安全问题的代码(访问了共享数据的代码)
     *     }
     */
    private synchronized void sellTicket() {
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
