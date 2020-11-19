package com.rice.algorithm.test;

import com.rice.algorithm.linear.Stack;
import org.junit.Test;

/**
 * @author wgz
 * @description 栈测试类
 * @date 2020/11/19 17:23
 */
public class StackTest {

    @Test
    public void testStack() {

        Stack<String> stack = new Stack<>();

        stack.push("AntiMaga");
        stack.push("Viper");
        stack.push("Invoker");
        stack.push("Axe");
        stack.push("Luna");

        stack.forEach(System.out::println);

        String result = stack.pop();
        System.out.println("弹出的元素是：" + result);
        System.out.println("剩余的元素个数：" + stack.size());
    }
}
