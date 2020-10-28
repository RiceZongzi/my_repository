package com.rice.stream;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wgz
 * @description
 * @date 2020/10/27 10:57
 */
public class StreamDemo {

    public static void main(String[] args) {
        forEachDemo();
    }

    private static void forEachDemo() {
        List<String> list = new ArrayList<>();
        list.add("AntiMage");
        list.add("Viper");
        list.add("Invoker");
        list.add("Axe");
        list.add("Luna");
        // Traditional Way
//        1. 首先筛选所有A开头的人；
        List<String> startWithA = new ArrayList<>();
        for (String s : list) {
            if (s.startsWith("A")) {
                startWithA.add(s);
            }
        }
//        2. 然后筛选名字短于五个字母的人；
        List<String> lessFive = new ArrayList<>();
        for (String s : startWithA) {
            if (s.length() < 5) {
                lessFive.add(s);
            }
        }
//        3. 最后进行对结果进行打印输出。
        for (String s : lessFive) {
            System.out.println(s);
        }

        // By Stream
        list.stream()
                // 筛选所有A开头的人
                .filter(s -> s.startsWith("A"))
                // 筛选名字短于五个字母的人
                .filter(s -> s.length() < 5)
                // 对结果进行打印输出
                .forEach(System.out::println);
    }
}
