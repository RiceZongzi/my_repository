package com.rice.algorithm.linear;

import java.util.Iterator;

/**
 * @author wgz
 * @description 队列
 * @version v1.0 基础API实现
 * @date 2020/11/19 17:40
 */
public class Queue<T> implements Iterable<T> {

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

    /**
     * 判断当前栈是否为空
     * @author wgz
     * @date 2020/11/20
     * @return boolean
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 获取栈中元素的个数
     * @author wgz
     * @date 2020/11/20
     * @return int
     */
    public int size() {
        return n;
    }

    /**
     * 向队列中插入元素t
     * @author wgz
     * @date 2020/11/20
     * @param t 元素
     */
    public void enqueue(T t) {
        if (last == null) {
            // 当前尾结点last为null
            last = new Node(t, null);
            head.next = last;
        } else {
            // 当前尾结点last不为null
            Node oldLast = last;
            last = new Node(t, null);
            oldLast.next = last;
        }
        // 元素个数+1
        n++;
    }

    /**
     * 从队列中拿出一个元素
     * @author wgz
     * @date 2020/11/20
     * @return T
     */
    public T dequeue() {
        if (isEmpty()){
            return null;
        }
        Node oldFirst = head.next;
        head.next = oldFirst.next;
        n--;

        // 因为出队列其实是在删除元素，因此如果队列中的元素被删除完了，需要重置last=null;

        if (isEmpty()){
            last=null;
        }
        return oldFirst.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new QIterator();
    }

    private class QIterator implements Iterator {
        private Node n;

        public QIterator(){
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
