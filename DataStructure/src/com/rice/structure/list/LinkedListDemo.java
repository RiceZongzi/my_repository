package com.rice.structure.list;

import java.util.LinkedList;
import java.util.Random;

/**
 * @author wgz
 * @description
 * @date 2020/10/21 14:21
 */
public class LinkedListDemo {

    /**
     * java.util.LinkedList集合 implements List接口
     *     LinkedList集合的特点:
     *         1.底层是一个链表结构:查询慢,增删快
     *         2.里边包含了大量操作首尾元素的方法
     *         注意:使用LinkedList集合特有的方法,不能使用多态
     *
     *         - public void addFirst(E e):将指定元素插入此列表的开头。
     *         - public void addLast(E e):将指定元素添加到此列表的结尾。
     *         - public void push(E e):将元素推入此列表所表示的堆栈，此方法等效于 addFirst(E)。
     *
     *         - public E getFirst():返回此列表的第一个元素。
     *         - public E getLast():返回此列表的最后一个元素。
     *
     *         - public E removeFirst():移除并返回此列表的第一个元素。
     *         - public E removeLast():移除并返回此列表的最后一个元素。
     *         - public E pop():从此列表所表示的堆栈处弹出一个元素，此方法相当于 removeFirst()。
     *
     *         - public boolean isEmpty()：如果列表不包含元素，则返回true。
     */

    private static LinkedList<Integer> list = new LinkedList<>();

    private static Random random = new Random();

    public static void main(String[] args) {
        init();
        show();

        add(random.nextInt(100), random.nextInt(100));
        show();

        remove();
        show();
    }

    private static void add(int left, int right) {
        list.addFirst(left);
        list.addLast(right);
        System.out.println(list);
    }

    private static void remove() {
        list.removeFirst();
        list.removeLast();
        System.out.println(list);
    }

    private static void show() {
        if(!list.isEmpty()){
            System.out.println("get first(" + list.getFirst() + ")");
            System.out.println("get last(" + list.getLast() + ")");;
        }
    }

    private static void init() {
        for (int i = 0; i < 5; i++) {
            list.add(random.nextInt(100));
        }
        System.out.println(list);
    }
}
