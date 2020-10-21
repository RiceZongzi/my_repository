package com.rice.structure.set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author wgz
 * @description
 * @date 2020/10/21 15:54
 */
public class LinkedHashSetDemo {
    /**
     * java.util.LinkedHashSet集合 extends HashSet集合
     *     LinkedHashSet集合特点:
     *         底层是一个哈希表(数组+链表/红黑树)+链表:
     *         多了一条链表(记录元素的存储顺序),保证元素有序
     */

    private static Set<Integer> set = new HashSet<>();

    private static LinkedHashSet<Integer> zSet = new LinkedHashSet<>();

    private static Random random = new Random();

    public static void main(String[] args) {
        init();
    }

    private static void init() {
        for (int i = 0; i < 10; i++) {
            set.add(random.nextInt(4));
            zSet.add(random.nextInt(4));
        }
        System.out.println(set);
        System.out.println(zSet);
    }
}
