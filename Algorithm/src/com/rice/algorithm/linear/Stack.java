package com.rice.algorithm.linear;

import java.util.Iterator;

/**
 * @author wgz
 * @description 栈
 * @version v1.0 基础API实现
 * @date 2020/11/19 16:51
 */
public class Stack<T> implements Iterable<T>{

    /** 记录首结点*/
    private Node head;

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
    public Stack() {
        this.head = new Node(null, null);
        this.n = 0;
    }

    /**
     * 判断当前栈是否为空
     * @author wgz
     * @date 2020/11/19
     * @return boolean
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 获取栈中元素的个数
     * @author wgz
     * @date 2020/11/19
     * @return int
     */
    public int size() {
        return n;
    }

    /**
     * 把t元素压入栈
     * @author wgz
     * @date 2020/11/19
     * @param t T
     */
    public void push(T t){
        // 找到首结点指向的第一个结点
        Node oldFirst = head.next;
        // 创建新结点
        Node newNode = new Node(t, null);
        // 让首结点指向新结点
        head.next = newNode;
        // 让新结点指向原来的第一个结点
        newNode.next = oldFirst;
        // 元素个数+1；
        n++;
    }

    /**
     * 弹出栈顶元素
     * @author wgz
     * @date 2020/11/19
     * @return T
     */
    public T pop(){
        //找到首结点指向的第一个结点
        Node oldFirst = head.next;
        if (oldFirst == null){
            return null;
        }
        //让首结点指向原来第一个结点的下一个结点
        head.next = oldFirst.next;
        //元素个数-1；
        n--;
        return oldFirst.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new SIterator();
    }

    private class SIterator implements Iterator{
        private Node n;

        public SIterator(){
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
