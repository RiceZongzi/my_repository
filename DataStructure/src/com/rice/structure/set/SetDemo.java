package com.rice.structure.set;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author wgz
 * @description
 * @date 2020/10/21 15:29
 */
public class SetDemo {

    /**
     * java.util.Set接口 extends Collection接口
     *     Set接口的特点:
     *         1.不允许存储重复的元素
     *         2.没有索引,没有带索引的方法,也不能使用普通的for循环遍历
     *     java.util.HashSet集合 implements Set接口
     *     HashSet特点:
     *          1.不允许存储重复的元素
     *          2.没有索引,没有带索引的方法,也不能使用普通的for循环遍历
     *          3.是一个无序的集合,存储元素和取出元素的顺序有可能不一致
     *          4.底层是一个哈希表结构(查询的速度非常的快)
     */

    private static Set<Integer> set = new HashSet<>();

    private static Random random = new Random();

    public static void main(String[] args) {
        init();
    }

    private static void init() {
        for (int i = 0; i < 10; i++) {
            set.add(random.nextInt(4));
        }
        System.out.println(set);
    }
}
