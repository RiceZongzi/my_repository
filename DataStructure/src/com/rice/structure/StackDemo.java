package com.rice.structure;

import java.util.Random;
import java.util.Stack;

/**
 * @author wgz
 * @description
 * @date 2020/10/20 16:24
 */
public class StackDemo {
    private static Stack<Integer> stack = new Stack<>();

    private static Random random = new Random();

    public static void main(String[] args) {
        init();
        System.out.println(stack);
        push(random.nextInt(100));
    }

    private static void push(int num) {
        stack.push(num);
        System.out.println("push(" + num + ")");
        System.out.println("stack: " + stack);
    }

    private static void pop() {
        System.out.print("pop -> " + stack.pop());
        System.out.println("stack: " + stack);
    }

    private static void init() {
        for (int i = 0; i < 5; i++) {
            stack.push(random.nextInt(100));
        }
    }
}
