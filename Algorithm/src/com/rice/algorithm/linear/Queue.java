package com.rice.algorithm.linear;

/**
 * @author wgz
 * @description 队列
 * @version v1.0 基础API实现
 * @date 2020/11/19 17:40
 */
public class Queue<T> {

    /** 记录首结点*/
    private Node head;

    /** 记录最后一个结点*/
    private Node last;

    /** 栈中元素的个数*/
    private int n;

    /**
     * 结点类
     * @author wgz
     * @date 2020/11/19
     */
    private class Node {
        /** 存储数据*/
        T item;

        /** 下一个结点*/
        Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    /**
     * 构造方法
     * @author wgz
     * @date 2020/11/19
     */
    public Queue() {
        this.head = new Node(null, null);
        this.last = null;
        this.n = 0;
    }
}
