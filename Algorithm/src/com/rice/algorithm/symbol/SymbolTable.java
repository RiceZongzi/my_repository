package com.rice.algorithm.symbol;

/**
 * @author wgz
 * create date  2020/11/20 0:25
 */
public class SymbolTable<Key, Value> {

    /** 记录首结点*/
    private Node head;
    /** 记录符号表中元素的个数*/
    private int n;

    private class Node {
        // 键
        public Key key;
        // 值
        public Value value;
        // 下一个结点
        public Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public SymbolTable() {
        this.head = new Node(null, null, null);
        this.n = 0;
    }

    /**
     * 获取符号表中键值对的个数
     * @author wgz
     * create date 2020/11/20 0:27
     * @return int
     */
    public int size() {
        return n;
    }

    /**
     * 往符号表中插入键值对
     * @author wgz
     * create date 2020/11/20 0:28
     * @param key 键
     * @param value 值
     */
    public void put(Key key, Value value) {
        // 符号表中已经存在了键为key的键值对，
        // 那么只需要找到该结点，替换值为value即可
        Node node = head;
        while(node.next != null) {
            // 变换node
            node = node.next;
            // 判断n结点存储的键是否为key，如果是，则替换node结点的值
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }

        // 如果符号表中不存在键为key的键值对，
        // 只需要创建新的结点，保存要插入的键值对，把新结点插入到链表的头部  head.next=新结点即可
        Node newNode = new Node(key, value, null);
        newNode.next = head.next;
        head.next = newNode;

        // 元素个数+1；
        n++;
    }

    /**
     * 删除符号表中键为key的键值对
     * @author wgz
     * create date 2020/11/20 0:31
     * @param key 键
     */
    public void delete(Key key) {
        // 找到键为key的结点，把该结点从链表中删除
        Node node = head;
        while (node.next != null) {
            // 判断node结点的下一个结点的键是否为key，如果是，就删除该结点
            if (node.next.key.equals(key)) {
                node.next = node.next.next;
                n--;
                return;
            }
            // 变换node
            node = node.next;
        }
    }

    /**
     * 从符号表中获取key对应的值
     * @author wgz
     * create date 2020/11/20 0:32
     * @param key 键
     * @return Value
     */
    public Value get(Key key) {
        // 找到键为key的结点
        Node node = head;
        while(node.next != null) {
            // 变换node
            node = node.next;
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }
}
