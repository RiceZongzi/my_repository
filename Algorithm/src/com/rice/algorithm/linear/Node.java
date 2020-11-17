package com.rice.algorithm.linear;

/**
 * @author wgz
 * @description 链表节点类
 * @version v1.0 基础API实现
 * @date 2020/11/16 18:59
 */
public class Node<T> {

    /** 存储元素*/
    public T item;

    /** 指向下一个节点*/
    public Node next;

    /**
     * 构造方法
     * @author wgz
     * @date 2020/11/16
     * @param item
     * @param next
     */
    public Node(T item, Node next) {
        this.item = item;
        this.next = next;
    }
}
