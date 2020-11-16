package com.rice.algorithm.linear;

import org.junit.Test;

/**
 * @author wgz
 * @description 线性表测试类
 * @date 2020/11/13 17:11
 */
public class LinearTest {

    @Test
    public void sequenceListTest() {
        SequenceList<Integer> list = new SequenceList<>(4);
        list.insert(0);
        list.insert(1);
        list.insert(2);
        // 测试定点插入
        list.insert(2, 9);
        System.out.println("索引为2的结果为：" + list.get(2));
        // 测试删除
        Integer removeResult = list.remove(0);
        System.out.println("删除的元素是：" + removeResult);
        // 测试清空
        System.out.println("清空前的线性表中的元素个数为：" + list.length());
        list.clear();
        System.out.println("清空后的线性表中的元素个数为：" + list.length());
    }

    @Test
    public void sequenceListTest2() {
        SequenceList<Integer> list = new SequenceList<>(4);
        list.insert(0);
        list.insert(1);
        list.insert(2);

        list.forEach(System.out::println);
    }
}
