package com.rice.structure.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author wgz
 * @description
 * @date 2020/10/21 13:59
 */
public class ListDemo {

    /**
     * java.util.List接口 extends Collection接口
     *     List接口的特点:
     *         1.有序的集合,存储元素和取出元素的顺序是一致的(存储123 取出123)
     *         2.有索引,包含了一些带索引的方法
     *         3.允许存储重复的元素
     *
     *     List接口中带索引的方法(特有)
     *         - public void add(int index, E element): 将指定的元素，添加到该集合中的指定位置上。
     *         - public E get(int index):返回集合中指定位置的元素。
     *         - public E remove(int index): 移除列表中指定位置的元素, 返回的是被移除的元素。
     *         - public E set(int index, E element):用指定元素替换集合中指定位置的元素,返回值的更新前的元素。
     *     注意:
     *         操作索引的时候,一定要防止索引越界异常
     *         IndexOutOfBoundsException:索引越界异常,集合会报
     *         ArrayIndexOutOfBoundsException:数组索引越界异常
     *         StringIndexOutOfBoundsException:字符串索引越界异常
     */
    private static List<Integer> list = new ArrayList<>();

    private static Random random = new Random();

    public static void main(String[] args) {
        init();
        System.out.println(list);

        // 指定位置添加元素
        add(random.nextInt(5), random.nextInt(100));

        // 移除元素
        remove(random.nextInt(5));

        // 替换
        instead(random.nextInt(5), random.nextInt(100));
    }

    private static void instead(int index, int num) {
//        替换index索引处的元素为num。
        list.set(index, num);
        System.out.println("set(" + num + ")， where(" + index + ")");
        System.out.println("list: " + list);
    }

    private static void remove(int index) {
//        移除index索引处的元素。
        System.out.println("remove(" + list.remove(index) + ")， where(" + index + ")");
        System.out.println("list: " + list);
    }

    private static void add(int index, int num) {
//        在index索引处添加元素num。
        list.add(index, num);
        System.out.println("add(" + num + ")， where(" + index + ")");
        System.out.println("list: " + list);
    }

    private static void init() {
        for (int i = 0; i < 5; i++) {
            list.add(random.nextInt(100));
        }
    }
}
