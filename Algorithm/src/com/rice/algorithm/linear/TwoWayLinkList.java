package com.rice.algorithm.linear;

import java.util.Iterator;

/**
 * @author wgz
 * @version v1.0 基础API实现
 * @description 双向链表
 * @date 2020/11/17 16:06
 */
public class TwoWayLinkList<T> implements Iterable<T> {

    /** 首节点 */
    private Node head;

    /** 尾节点 */
    private Node last;

    /** 链表长度 */
    private int n;

    /**
     * 结点类
     * @author wgz
     * @date 2020/11/17
     */
    private class Node {

        /** 存储数据 */
        public T item;

        /** 指向上一个结点 */
        public Node pre;

        /** 指向下一个结点*/
        public Node next;

        public Node(T item, Node pre, Node next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }
    }

    /**
     * 构造函数
     * @author wgz
     * @date 2020/11/17
     */
    public TwoWayLinkList() {
        // 初始化头结点和尾结点
        this.head = new Node(null,null,null);
        this.last = null;
        // 初始化元素个数
        this.n = 0;
    }

    /**
     * 清空链表
     * @author wgz
     * @date 2020/11/16
     */
    public void clear() {
        head.next = null;
        head.pre = null;
        head.item = null;
        last = null;
        n = 0;
    }

    /**
     * 判断当前链表是否为空
     * @author wgz
     * @date 2020/11/16
     * @return boolean
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 获取链表的长度
     * @author wgz
     * @date 2020/11/16
     * @return int
     */
    public int length() {
        return n;
    }

    /**
     * 获取第一个元素
     * @author wgz
     * @date 2020/11/17
     * @return T
     */
    public T getFirst(){
        if (isEmpty()){
            return null;
        }
        return head.next.item;
    }

    /**
     * 获取最后一个元素
     * @author wgz
     * @date 2020/11/17
     * @return T
     */
    public T getLast(){
        if (isEmpty()){
            return null;
        }
        return last.item;
    }

    /**
     * 插入元素t
     * @author wgz
     * @date 2020/11/17
     * @param t T
     */
    public void insert(T t){
        if (isEmpty()){
            // 如果链表为空，创建新的结点，让新结点称为尾结点
            last = new Node(t, head, null);
            // 让头结点指向尾结点
            head.next=last;
        } else {
            // 如果链表不为空
            Node oldLast = last;
            // 创建新的结点
            Node newNode = new Node(t, oldLast, null);
            // 让当前的尾结点指向新结点
            oldLast.next=newNode;
            // 让新结点称为尾结点
            last = newNode;
        }
        // 元素个数+1
        n++;

    }

    /**
     * 向指定位置i处插入元素t
     * @author wgz
     * @date 2020/11/17
     * @param i 索引
     * @param t T
     */
    public void insert(int i,T t){
        // 找到i位置的前一个结点
        Node pre = head;
        for(int index = 0; index < i; index++){
            pre = pre.next;
        }
        // 找到i位置的结点
        Node curr = pre.next;
        // 创建新结点
        Node newNode = new Node(t, pre, curr);
        // 让i位置的前一个结点的下一个结点变为新结点
        pre.next = newNode;
        // 让i位置的前一个结点变为新结点
        curr.pre = newNode;
        // 元素个数+1
        n++;
    }

    /**
     * 获取指定位置i处的元素
     * @author wgz
     * @date 2020/11/17
     * @param i 索引
     * @return T
     */
    public T get(int i){
        Node n = head.next;
        for(int index = 0; index < i; index++){
            n = n.next;
        }
        return n.item;
    }

    /**
     * 找到元素t在链表中第一次出现的位置
     * @author wgz
     * @date 2020/11/17
     * @param t T
     * @return int
     */
    public int indexOf(T t){
        Node n = head;
        for(int i = 0; n.next != null; i++){
            n = n.next;
            if (n.next.equals(t)){
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除位置i处的元素，并返回该元素
     * @author wgz
     * @date 2020/11/17
     * @param i 索引
     * @return T
     */
    public T remove(int i){
        // 找到i位置的前一个结点
        Node pre = head;
        for(int index = 0; index < i; index++){
            pre = pre.next;
        }
        // 找到i位置的结点
        Node curr = pre.next;
        // 找到i位置的下一个结点
        Node nextNode = curr.next;
        // 让i位置的前一个结点的下一个结点变为i位置的下一个结点
        pre.next = nextNode;
        // 让i位置的下一个结点的上一个结点变为i位置的前一个结点
        nextNode.pre = pre;
        // 元素的个数-1
        n--;
        return curr.item;
    }

    @Override
    public Iterator iterator() {
        return new TIterator();
    }

    private class TIterator implements Iterator{

        private Node n;

        public TIterator(){
            this.n = head;
        }

        @Override
        public boolean hasNext() {
            return n.next != null;
        }

        @Override
        public Object next() {
            n = n.next;
            return n.item;
        }
    }
}
