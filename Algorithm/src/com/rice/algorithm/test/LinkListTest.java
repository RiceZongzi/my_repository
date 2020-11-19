package com.rice.algorithm.test;

import com.rice.algorithm.linear.LinkList;
import org.junit.Test;

/**
 * @author wgz
 * @description 单向链表测试类
 * @date 2020/11/19 17:21
 */
public class LinkListTest {

    @Test
    public void linkListTest() {
        LinkList<String> linkList = new LinkList<>();

        linkList.insert("AntiMaga");
        linkList.insert("Viper");
        linkList.insert("Invoker");
        linkList.insert("Axe");
        linkList.insert(1,"Luna");

        System.out.println("单向链表的长度为：" + linkList.length());
        System.out.println("单向链表索引为2的元素为：" + linkList.get(2));
        linkList.forEach(System.out::println);
        System.out.println("------------------------------------------");
        System.out.println("单向链表移除索引为1的元素为：" + linkList.remove(1));
        linkList.forEach(System.out::println);
        System.out.println("单向链表的长度为：" + linkList.length());
    }
}
