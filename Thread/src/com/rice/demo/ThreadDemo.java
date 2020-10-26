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
//        method7();
//        waitAndNotify1();
        waitAndNotify2();
    }

    /**
     * 进入到TimeWaiting(计时等待)有两种方式
     *     1.使用sleep(long m)方法,在毫秒值结束之后,线程睡醒进入到Runnable/Blocked状态
     *     2.使用wait(long m)方法,wait方法如果在毫秒值结束之后,还没有被notify唤醒,就会自动醒来,线程睡醒进入到Runnable/Blocked状态
     *
     *     唤醒的方法:
     *          void notify() 唤醒在此对象监视器上等待的单个线程。
     *          void notifyAll() 唤醒在此对象监视器上等待的所有线程。
     */
    private static void waitAndNotify2() {
        // 创建锁对象,保证唯一
        Object obj = new Object();
        // 创建一个顾客线程(消费者)
        new Thread(() -> {
            // 一直等着买包子
            while(true){
                // 保证等待和唤醒的线程只能有一个执行,需要使用同步技术
                synchronized (obj){
                    System.out.println("顾客1告知老板要的包子的种类和数量");
                    // 调用wait方法,放弃cpu的执行,进入到WAITING状态(无限等待)
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 唤醒之后执行的代码
                    System.out.println("包子已经做好了,顾客1开吃!");
                    System.out.println("---------------------------------------");
                }
            }
        }).start();

        // 创建一个顾客线程(消费者)
        new Thread(() -> {
            // 一直等着买包子
            while(true){
                // 保证等待和唤醒的线程只能有一个执行,需要使用同步技术
                synchronized (obj){
                    System.out.println("顾客2告知老板要的包子的种类和数量");
                    // 调用wait方法,放弃cpu的执行,进入到WAITING状态(无限等待)
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 唤醒之后执行的代码
                    System.out.println("包子已经做好了,顾客2开吃!");
                    System.out.println("---------------------------------------");
                }
            }
        }).start();

        // 创建一个老板线程(生产者)
        new Thread(() -> {
            // 一直做包子
            while (true){
                try {
                    // 花了5秒做包子
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 保证等待和唤醒的线程只能有一个执行,需要使用同步技术
                synchronized (obj){
                    System.out.println("老板5秒钟之后做好包子,告知顾客,可以吃包子了");
                    // 做好包子之后,调用notify方法,唤醒顾客吃包子
                    // 如果有多个等待线程,随机唤醒一个
                    // obj.notify();
                    // 唤醒所有等待的线程
                    obj.notifyAll();
                }
            }
        }).start();
    }

    /**
     * 等待唤醒案例:线程之间的通信
     *         创建一个顾客线程(消费者):告知老板要的包子的种类和数量,调用wait方法,放弃cpu的执行,进入到WAITING状态(无限等待)
     *         创建一个老板线程(生产者):花了5秒做包子,做好包子之后,调用notify方法,唤醒顾客吃包子
     *
     *     注意:
     *         顾客和老板线程必须使用同步代码块包裹起来,保证等待和唤醒只能有一个在执行
     *         同步使用的锁对象必须保证唯一
     *         只有锁对象才能调用wait和notify方法
     *
     *     Obejct类中的方法
     *     void wait()
     *           在其他线程调用此对象的 notify() 方法或 notifyAll() 方法前，导致当前线程等待。
     *     void notify()
     *           唤醒在此对象监视器上等待的单个线程。
     *           会继续执行wait方法之后的代码
     */
    private static void waitAndNotify1() {
        // 创建锁对象,保证唯一
        Object obj = new Object();
        // 创建一个顾客线程(消费者)
        new Thread(() -> {
            // 一直等着买包子
            while(true){
                // 保证等待和唤醒的线程只能有一个执行,需要使用同步技术
                synchronized (obj){
                    System.out.println("告知老板要的包子的种类和数量");
                    // 调用wait方法,放弃cpu的执行,进入到WAITING状态(无限等待)
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 唤醒之后执行的代码
                    System.out.println("包子已经做好了,开吃!");
                    System.out.println("---------------------------------------");
                }
            }
        }).start();

        // 创建一个老板线程(生产者)
        new Thread(() -> {
            // 一直做包子
            while (true){
                try {
                    // 花了5秒做包子
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 保证等待和唤醒的线程只能有一个执行,需要使用同步技术
                synchronized (obj){
                    System.out.println("老板5秒钟之后做好包子,告知顾客,可以吃包子了");
                    // 做好包子之后,调用notify方法,唤醒顾客吃包子
                    obj.notify();
                }
            }
        }).start();
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
     *
     * 匿名内部类方式实现线程的创建
     *
     *     匿名:没有名字
     *     内部类:写在其他类内部的类
     *
     *     匿名内部类作用:简化代码
     *         把子类继承父类,重写父类的方法,创建子类对象合一步完成
     *         把实现类实现类接口,重写接口中的方法,创建实现类对象合成一步完成
     *     匿名内部类的最终产物:子类/实现类对象,而这个类没有名字
     *
     *     格式:
     *         new 父类/接口(){
     *             重复父类/接口中的方法
     *         };
     */
    private static void method3() {

        Runnable r = () -> {
            System.out.println("This is NoNameInnerClass");
            for (int i = 0; i < 50; i++) {
                System.out.println("NoNameInnerClass" + i);
            }
        };

        new Thread(r).start();

        System.out.println("This is main");
        for (int i = 0; i < 50; i++) {
            System.out.println("main" + i);
        }
    }
}
