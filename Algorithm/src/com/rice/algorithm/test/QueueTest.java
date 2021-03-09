package com.rice.algorithm.test;

import com.rice.algorithm.linear.Queue;
import org.junit.Test;

/**
 * @author wgz
 * @description 队列测试类
 * @date 2020/11/20 9:54
 */
public class QueueTest {

    @Test
    public void queueTest() {

        Queue<String> queue = new Queue<>();

        queue.enqueue("AntiMaga");
        queue.enqueue("Viper");
        queue.enqueue("Invoker");
        queue.enqueue("Axe");
        queue.enqueue("Luna");

        queue.forEach(System.out::println);

        String result = queue.dequeue();
        System.out.println("出队列的元素是：" + result);
        System.out.println("剩余的元素个数：" + queue.size());
    }
}
