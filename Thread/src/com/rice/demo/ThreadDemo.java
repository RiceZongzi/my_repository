package com.rice.demo;

import com.rice.thread.*;

/**
 * @author wgz
 * @description
 * @date 2020/10/23 16:33
 */
public class ThreadDemo {

    /**
     * 实现Runnable接口比继承Thread类所具有的优势：
     *      适合多个相同的程序代码的线程去共享同一个资源。
     *      可以避免java中的单继承的局限性。
     *      增加程序的健壮性，实现解耦操作，代码可以被多个线程共享，代码和线程独立。
     *      线程池只能放入实现Runable或Callable类线程，不能直接放入继承Thread的类。
     */

    public static void main(String[] args) {
//        method1();
//        method2();
//        method3();
//        method4();
//        method5();
//        method6();
        method7();
    }

    private static void method7() {
        // 创建线程任务对象
        TicketWithLock ticket = new TicketWithLock();
        // 创建三个窗口对象
        Thread t1 = new Thread(ticket, "窗口1");
        Thread t2 = new Thread(ticket, "窗口2");
        Thread t3 = new Thread(ticket, "窗口3");
        // 同时卖票
        t1.start();
        t2.start();
        t3.start();
    }

    private static void method6() {
        // 创建线程任务对象
        TicketWithMethod ticket = new TicketWithMethod();
        // 创建三个窗口对象
        Thread t1 = new Thread(ticket, "窗口1");
        Thread t2 = new Thread(ticket, "窗口2");
        Thread t3 = new Thread(ticket, "窗口3");
        // 同时卖票
        t1.start();
        t2.start();
        t3.start();
    }

    private static void method5() {
        // 创建线程任务对象
        TicketWithBlock ticket = new TicketWithBlock();
        // 创建三个窗口对象
        Thread t1 = new Thread(ticket, "窗口1");
        Thread t2 = new Thread(ticket, "窗口2");
        Thread t3 = new Thread(ticket, "窗口3");
        // 同时卖票
        t1.start();
        t2.start();
        t3.start();
    }

    private static void method4() {
        // 创建线程任务对象
        Ticket ticket = new Ticket();
        // 创建三个窗口对象
        Thread t1 = new Thread(ticket, "窗口1");
        Thread t2 = new Thread(ticket, "窗口2");
        Thread t3 = new Thread(ticket, "窗口3");
        // 同时卖票
        t1.start();
        t2.start();
        t3.start();
    }

    private static void method1() {
        MyThreadByExtendsThread t = new MyThreadByExtendsThread("tThread");
        t.start();
        System.out.println("This is main");
        for (int i = 0; i < 50; i++) {
            System.out.println("main" + i);
        }
    }


    /**
     * 启动的多线程的时候
     * 需要先通过Thread类的构造方法Thread(Runnable target) 构造出对象
     * 然后调用Thread对象的start()方法来运行多线程代码
     */
    private static void method2() {
        MyThreadByImplementsRunnable r = new MyThreadByImplementsRunnable();
        Thread thread = new Thread(r, "tRunnable");
        thread.start();
        System.out.println("This is main");
        for (int i = 0; i < 50; i++) {
            System.out.println("main" + i);
        }
    }

    /**
     * 使用匿名内部类的方式实现Runnable接口
     * 重新Runnable接口中的run方法
     */
    private static void method3() {

        Runnable r = new Runnable(){

            @Override
            public void run() {
                System.out.println("This is NoNameInnerClass");
                for (int i = 0; i < 50; i++) {
                    System.out.println("NoNameInnerClass" + i);
                }
            }
        };

        new Thread(r).start();

        System.out.println("This is main");
        for (int i = 0; i < 50; i++) {
            System.out.println("main" + i);
        }
    }
}
