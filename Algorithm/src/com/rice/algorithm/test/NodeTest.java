package com.rice.algorithm.test;

import com.rice.algorithm.linear.Node;
import org.junit.Test;

/**
 * @author wgz
 * @description Node测试类
 * @date 2020/11/16 19:15
 */
public class NodeTest {

    @Test
    public void nodeTest() {
        // 构建节点
        Node<Integer> first = new Node<Integer>(11, null);
        Node<Integer> second = new Node<Integer>(13, null);
        Node<Integer> third = new Node<Integer>(12, null);
        Node<Integer> fourth = new Node<Integer>(8, null);
        Node<Integer> fifth = new Node<Integer>(9, null);

        // 生成链表
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
    }
}
