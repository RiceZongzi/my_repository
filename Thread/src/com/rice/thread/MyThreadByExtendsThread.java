package com.rice.thread;

/**
 * @author wgz
 * @description
 *      常用构造：
 *          public Thread()
 *              分配一个新的线程对象。
 *          public Thread(String name)
 *              分配一个指定名字的新的线程对象。
 *          public Thread(Runnable target)
 *              分配一个带有指定目标新的线程对象。
 *          public Thread(Runnable target,String name)
 *              分配一个带有指定目标新的线程对象并指定名字。
 *      常用方法：
 *          public String getName()
 *              获取当前线程名称。
 *          public void start()
 *              导致此线程开始执行; Java虚拟机调用此线程的run方法。
 *          public void run()
 *              此线程要执行的任务在此处定义代码。
 *          public static void sleep(long millis)
 *              使当前正在执行的线程以指定的毫秒数暂停（暂时停止执行）。
 *          public static Thread currentThread()
 *              返回对当前正在执行的线程对象的引用。
 * @date 2020/10/23 16:33
 */
public class MyThreadByExtendsThread extends Thread{

    /**
     * 利用继承中的特点
     * 将线程名称传递 进行设置
     */
    public MyThreadByExtendsThread(String name){
        super(name);
    }

    /**
     * 重写run方法
     * 定义线程要执行的代码
     */
    @Override
    public void run(){
        System.out.println("This is " + getName());
        for (int i = 0; i < 50; i++) {
            // getName()方法，继承自父类
            System.out.println(getName() + i);
        }
    }
}
