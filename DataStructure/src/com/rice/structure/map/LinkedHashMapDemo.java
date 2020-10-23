package com.rice.structure.map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author wgz
 * @description
 * @date 2020/10/23 15:03
 */
public class LinkedHashMapDemo {

    /**
     * java.util.LinkedHashMap<K,V> entends HashMap<K,V>
     *     Map 接口的哈希表和链接列表实现，具有可预知的迭代顺序。
     *     底层原理:
     *         哈希表+链表(记录元素的顺序)
     */
    private static Map<Integer, Integer> map = new HashMap<>();

    private static LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<>();

    private static Random random = new Random();

    public static void main(String[] args) {
        init();
    }

    private static void init() {
        int[] keyArray = new int[]{1, 3, 5, 4, 2};
        for (int i = 0; i < keyArray.length; i++) {
            int val = random.nextInt(100);
            map.put(keyArray[i], val);
            linkedHashMap.put(keyArray[i], val);
        }
        System.out.println(map);
        System.out.println(linkedHashMap);
    }
}
