package com.rice.structure.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author wgz
 * @description
 * @date 2020/10/22 15:45
 */
public class MapDemo {

    /**
     * java.util.Map<k,v>集合
     *     Map集合的特点:
     *         1.Map集合是一个双列集合,一个元素包含两个值(一个key,一个value)
     *         2.Map集合中的元素,key和value的数据类型可以相同,也可以不同
     *         3.Map集合中的元素,key是不允许重复的,value是可以重复的
     *         4.Map集合中的元素,key和value是一一对应
     *     java.util.HashMap<k,v>集合 implements Map<k,v>接口
     *     HashMap集合的特点:
     *         1.HashMap集合底层是哈希表:查询的速度特别的快
     *             JDK1.8之前:数组+单向链表
     *             JDK1.8之后:数组+单向链表|红黑树(链表的长度超过8):提高查询的速度
     *         2.hashMap集合是一个无序的集合,存储元素和取出元素的顺序有可能不一致
     *    java.util.LinkedHashMap<k,v>集合 extends HashMap<k,v>集合
     *    LinkedHashMap的特点:
     *         1.LinkedHashMap集合底层是哈希表+链表(保证迭代的顺序)
     *         2.LinkedHashMap集合是一个有序的集合,存储元素和取出元素的顺序是一致的
     */
    private static Map<Integer, Integer> map = new HashMap<>();

    private static Random random = new Random();

    public static void main(String[] args) {
        init();
    }

    private static void init() {
        for (int i = 0; i < 5; i++) {
            map.put(i, random.nextInt(100));
        }
        System.out.println(map);
    }
}
