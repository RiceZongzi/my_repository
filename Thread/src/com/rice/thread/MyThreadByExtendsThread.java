package com.rice.thread;

/**
 * @author wgz
 * @description
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
