package com.rice.lambda;

/**
 * @author wgz
 * @description
 * @date 2020/10/27 10:56
 */
public class LambdaDemo {

    public static void main(String[] args) {
        showDiffByWaysToCreateThread();
    }

    private static void showDiffByWaysToCreateThread() {
//        By implements Runnable interface
        // 创建Runnable接口的实现类对象
        RunnableExample runnableExample = new RunnableExample();
        // 创建Thread类对象,构造方法中传递Runnable接口的实现类
        Thread thread = new Thread(runnableExample);
        // 调用start方法开启新线程,执行run方法
        thread.start();

//        By anonymous inner class
        Runnable r = new Runnable(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " Anonymous inner class 线程创建了");
            }
        };
        new Thread(r).start();
//        Or less code
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " Anonymous inner class 线程创建了");
            }
        }).start();

//        By lambda expression
        new Thread(() -> System.out.println(Thread.currentThread().getName() + " Lambda expression 线程创建了")).start();
    }
}
