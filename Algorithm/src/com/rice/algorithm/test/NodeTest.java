package com.rice.algorithm.test;

import com.rice.algorithm.linear.LinkList;
import com.rice.algorithm.linear.Node;
import org.junit.Test;

/**
 * @author wgz
 * @description 链表测试类
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

    @Test
    public void linkListTest() {
        LinkList<String> linkList = new LinkList<>();

        linkList.insert(0,"AntiMaga");
        linkList.insert(1,"Viper");
        linkList.insert(2,"Invoker");
        linkList.insert(3,"Axe");
        linkList.insert(4,"Luna");

        System.out.println("单向链表的长度为：" + linkList.length());
    }
}
