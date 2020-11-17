package com.rice.algorithm.linear;

import java.util.Iterator;

/**
 * @author wgz
 * @description 单向链表
 *      v1.0 基础API实现
 *      v1.1 实现链表反转
 * @date 2020/11/16 19:06
 */
public class LinkList<T> implements Iterable<T> {

    /** 记录头结点*/
    private Node head;

    /** 记录链表的长度*/
    private int n;

    /**
     * 构造方法
     * @author wgz
     * @date 2020/11/16
     */
    public LinkList() {
        // 初始化头节点
        this.head = new Node(null, null);
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
        head.item = null;
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
     * 获取指定位置的元素
     * @author wgz
     * @date 2020/11/16
     * @param i 索引
     * @return T
     */
    public T get(int i) {
        if (i < 0 || i >= n){
            throw new RuntimeException("位置不合法！");
        }
        Node n = head.next;
        for (int index = 0; index < i; index++) {
            n = n.next;
        }
        return (T) n.item;
    }

    /**
     * 向链表中添加元素
     * @author wgz
     * @date 2020/11/16
     * @param t T
     */
    public void insert(T t) {
        // 找到最后一个节点
        Node node = head;
        while(node.next != null){
            node = node.next;
        }
        Node newNode = new Node(t, null);
        node.next = newNode;
        // 链表长度+1n
        n++;
    }

    /**
     * 向指定位置i处，添加元素t
     * @author wgz
     * @date 2020/11/16
     * @param i 索引
     * @param t 元素
     */
    public void insert(int i, T t) {
        if (i < 0 || i >= n){
            throw new RuntimeException("位置不合法！");
        }
        // 寻找位置i之前的结点
        Node pre = head;
        for (int index = 0; index <= i - 1; index++) {
            pre = pre.next;
        }
        // 位置i的结点
        Node curr = pre.next;
        // 构建新的结点，让新结点指向位置i的结点
        Node newNode = new Node(t, curr);
        // 让之前的结点指向新结点
        pre.next = newNode;
        // 长度+1
        n++;
    }

    /**
     * 删除指定位置i处的元素，并返回被删除的元素
     * @author wgz
     * @date 2020/11/16
     * @param i 索引
     * @return T
     */
    public T remove(int i) {
        if (i < 0 || i >= n){
            throw new RuntimeException("位置不合法");
        }
        //  寻找i之前的元素
        Node pre = head;
        for (int index = 0; index <=i-1; index++) {
            pre = pre.next;
        }
        // 当前i位置的结点
        Node curr = pre.next;
        // 前一个结点指向下一个结点，删除当前结点
        pre.next = curr.next;
        // 长度-1
        n--;
        return (T) curr.item;
    }

    /**
     * 查找元素在链表中第一次出现的位置
     * @author wgz
     * @date 2020/11/16
     * @param t T
     * @return int
     */
    public int indexOf(T t) {
        Node node = head;
        for (int i = 0; node.next != null; i++){
            node = node.next;
            if (node.item.equals(t)){
                return i;
            }
        }
        return -1;
    }

    /**
     * 用来反转整个链表
     * @author wgz
     * @date 2020/11/17
     */
    public void reverse(){
        // 判断当前链表是否为空链表，
        // 如果是空链表，则结束运行，
        // 如果不是，则调用重载的reverse方法完成反转
        if (isEmpty()){
            return;
        }
        reverse(head.next);
    }

    /**
     * 反转指定的结点curr，并把反转后的结点返回
     * @author wgz
     * @date 2020/11/17
     * @param curr Node
     * @return com.rice.algorithm.linear.Node
     */
    public Node reverse(Node curr){
        if (curr.next==null){
            head.next=curr;
            return curr;
        }
        // 递归的反转当前结点curr的下一个结点；
        // 返回值就是链表反转后，当前结点的上一个结点
        Node pre = reverse(curr.next);
        // 让返回的结点的下一个结点变为当前结点curr；
        pre.next = curr;
        // 把当前结点的下一个结点变为null
        curr.next = null;
        return curr;
    }

    @Override
    public Iterator<T> iterator() {
        return new LIterator();
    }

    private class LIterator implements Iterator<T> {

        private Node node;

        public LIterator() {
            this.node = head;
        }

        @Override
        public boolean hasNext() {
            return node.next!=null;
        }

        @Override
        public T next() {
            node = node.next;
            return (T) node.item;
        }
    }
}
