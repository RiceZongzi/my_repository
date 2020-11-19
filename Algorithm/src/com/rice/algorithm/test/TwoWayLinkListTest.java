package com.rice.algorithm.test;

import com.rice.algorithm.linear.TwoWayLinkList;
import org.junit.Test;

/**
 * @author wgz
 * @description 双向链表测试类
 * @date 2020/11/19 17:22
 */
public class TwoWayLinkListTest {

    @Test
    public void twoWayLinkListTest() {
        TwoWayLinkList<String> twoWayLinkList = new TwoWayLinkList<>();

        twoWayLinkList.insert("AntiMaga");
        twoWayLinkList.insert("Viper");
        twoWayLinkList.insert("Invoker");
        twoWayLinkList.insert("Axe");
        twoWayLinkList.insert(1, "Luna");

        System.out.println("双向链表的第一个元素是：" + twoWayLinkList.getFirst());
        System.out.println("双向链表的最后一个元素是：" + twoWayLinkList.getLast());
        System.out.println("双向链表索引为2的元素为：" + twoWayLinkList.get(2));
        twoWayLinkList.forEach(System.out::println);
        System.out.println("双向链表的长度为：" + twoWayLinkList.length());
        System.out.println("------------------------------------------");
        System.out.println("双向链表移除索引为1的元素为：" + twoWayLinkList.remove(1));
        System.out.println("双向链表的长度为：" + twoWayLinkList.length());
        twoWayLinkList.clear();
        System.out.println("双向链表的长度为：" + twoWayLinkList.length());
    }
}
