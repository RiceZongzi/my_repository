package com.rice.structure;

import java.util.Random;
import java.util.Stack;

/**
 * @author wgz
 * @description
 * @date 2020/10/20 16:24
 */
public class StackDemo {

    /**
     * 仅允许在栈的顶端进行插入和删除操作
     * 先进后出（类比枪械的弹夹）
     *
     * 1    boolean empty()
     * 检验堆栈是否为空。
     *
     * 2	Object peek( )
     * 查看堆栈顶部的对象，但不从堆栈中移除它。
     *
     * 3	Object pop( )
     * 移除堆栈顶部的对象，并作为此函数的值返回该对象。
     *
     * 4	Object push(Object element)
     * 把项压入堆栈顶部。
     *
     * 5	int search(Object element)
     * 返回对象在堆栈中的位置，以 1 为基数。
     */
    private static Stack<Integer> stack = new Stack<>();

    private static Random random = new Random();

    public static void main(String[] args) {
        init();
        System.out.println(stack);
        push(random.nextInt(100));
        pop();
    }

    private static void push(int num) {
//        把项压入堆栈顶部。
        stack.push(num);
        System.out.println("push(" + num + ")");
        System.out.println("stack: " + stack);
//        查看堆栈顶部的对象，但不从堆栈中移除它。
        System.out.println("peek: " + stack.peek());
    }

    private static void pop() {
//        移除堆栈顶部的对象，并作为此函数的值返回该对象。
        System.out.println("pop -> " + stack.pop());
        System.out.println("stack: " + stack);
//        查看堆栈顶部的对象，但不从堆栈中移除它。
        System.out.println("peek: " + stack.peek());
    }

    private static void init() {
        for (int i = 0; i < 5; i++) {
            stack.push(random.nextInt(100));
        }
    }
}
