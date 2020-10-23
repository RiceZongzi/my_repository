package com.rice.structure.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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

        int val = 5 + random.nextInt(3);

        add(val , random.nextInt(100));

        contains(val);

        get(val);

        delete(val);

        contains(val);

        /*
        Map集合的第一种遍历方式:通过键找值的方式
        Map集合中的方法:
            Set<K> keySet() 返回此映射中包含的键的 Set 视图。
        实现步骤:
            1.使用Map集合中的方法keySet(),把Map集合所有的key取出来,存储到一个Set集合中
            2.遍历set集合,获取Map集合中的每一个key
            3.通过Map集合中的方法get(key),通过key找到value
         */
        Set<Integer> set = map.keySet();
        set.forEach(integer ->
                System.out.println("Key = " + integer
                        + ",Value = " + map.get(integer)));

        /*
        Map集合遍历的第二种方式:使用Entry对象遍历
        Map集合中的方法:
            Set<Map.Entry<K,V>> entrySet() 返回此映射中包含的映射关系的 Set 视图。
        实现步骤:
            1.使用Map集合中的方法entrySet(),把Map集合中多个Entry对象取出来,存储到一个Set集合中
            2.遍历Set集合,获取每一个Entry对象
            3.使用Entry对象中的方法getKey()和getValue()获取键与值
         */
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        entries.forEach(integerIntegerEntry ->
                System.out.println("Key = " + integerIntegerEntry.getKey()
                        + ",Value = " + integerIntegerEntry.getValue()));
    }

    /**
     * public V put(K key, V value):  把指定的键与指定的值添加到Map集合中。
     * 返回值:v
     *       存储键值对的时候,key不重复,返回值V是null
     *       存储键值对的时候,key重复,会使用新的value替换map中重复的value,返回被替换的value值
     */
    private static void add(int key, int value) {
        map.put(key, value);
        System.out.println(map);
    }

    /**
     * public V get(Object key) 根据指定的键，在Map集合中获取对应的值。
     * 返回值:
     *       key存在,返回对应的value值
     *       key不存在,返回null
     */
    private static void get(int key) {
        System.out.println(map.get(key));
    }

    /**
     * boolean containsKey(Object key) 判断集合中是否包含指定的键。
     *       包含返回true
     *       不包含返回false
     */
    private static void contains(int key) {
        System.out.println(map.containsKey(key));
    }

    /**
     * public V remove(Object key): 把指定的键 所对应的键值对元素 在Map集合中删除，返回被删除元素的值。
     * 返回值:V
     *       key存在,v返回被删除的值
     *       key不存在,v返回null
     */
    private static void delete(int key) {
        map.remove(key);
        System.out.println(map);
    }

    private static void init() {
        for (int i = 0; i < 5; i++) {
            map.put(i, random.nextInt(100));
        }
        System.out.println(map);
    }
}
