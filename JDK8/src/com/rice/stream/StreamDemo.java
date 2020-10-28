package com.rice.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wgz
 * @description
 * @date 2020/10/27 10:57
 */
public class StreamDemo {

    /**
     * java.util.stream.Stream<T>是Java 8新加入的最常用的流接口。（这并不是一个函数式接口。）
     *     获取一个流非常简单，有以下几种常用的方式：
     *         - 所有的Collection集合都可以通过stream默认方法获取流；
     *             default Stream<E> stream​()
     *         - Stream接口的静态方法of可以获取数组对应的流。
     *             static <T> Stream<T> of​(T... values)
     */
    public static void main(String[] args) {
//        forEachAndFilterDemo();
//        mapAndCollectDemo();
        countAndLimitAndSkip();
    }

    /**
     * Stream流中的常用方法_count:用于统计Stream流中元素的个数
     *     long count();
     *     count方法是一个终结方法,返回值是一个long类型的整数
     *     所以不能再继续调用Stream流中的其他方法了
     *
     * Stream流中的常用方法_limit:用于截取流中的元素
     *     limit方法可以对流进行截取，只取用前n个。方法签名：
     *     Stream<T> limit(long maxSize);
     *         参数是一个long型，如果集合当前长度大于参数则进行截取；否则不进行操作
     *     limit方法是一个延迟方法,只是对流中的元素进行截取，返回的是一个新的流，
     *         所以可以继续调用Stream流中的其他方法。
     *
     * Stream流中的常用方法_skip:用于跳过元素
     *     如果希望跳过前几个元素，可以使用skip方法获取一个截取之后的新流：
     *     Stream<T> skip(long n);
     *         如果流的当前长度大于n，则跳过前n个；否则将会得到一个长度为0的空流。
     */
    private static void countAndLimitAndSkip() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
        long count = list
                .stream()
                .filter(integer -> integer < 5)
                .count();
        System.out.println("list中，小于5的元素的个数为" + count);
        list.stream()
                .limit(count)
                .forEach(integer -> System.out.println("小于5的元素为" + integer));
        list.stream()
                .skip(count + 1)
                .forEach(integer -> System.out.println("大于5的元素为" + integer));
    }

    /**
     * Stream流中的常用方法_map:用于类型转换
     *     如果需要将流中的元素映射到另一个流中，可以使用map方法.
     *     <R> Stream<R> map(Function<? super T, ? extends R> mapper);
     *     该接口需要一个Function函数式接口参数，可以将当前流中的T类型数据转换为另一种R类型的流。
     *     Function中的抽象方法:
     *         R apply(T t);
     */
    private static void mapAndCollectDemo() {
        List<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");
        stringList.add("5");
        List<Integer> integerList = stringList
                .stream()
                // 把字符串类型的整数，转换(映射)为Integer类型的整数
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        integerList.forEach(System.out::println);
    }

    /**
     * Stream流中的常用方法_forEach
     *     void forEach(Consumer<? super T> action);
     *     该方法接收一个Consumer接口函数，会将每一个流元素交给该函数进行处理。
     *     Consumer接口是一个消费型的函数式接口,可以传递Lambda表达式,消费数据
     *
     *     简单记:
     *         forEach方法,用来遍历流中的数据
     *         是一个终结方法,遍历之后就不能继续调用Stream流中的其他方法
     *
     * Stream流中的常用方法_filter:
     *     用于对Stream流中的数据进行过滤
     *     Stream<T> filter(Predicate<? super T> predicate);
     *     filter方法的参数Predicate是一个函数式接口,所以可以传递Lambda表达式,对数据进行过滤
     *     Predicate中的抽象方法:
     *         boolean test(T t);
     */
    private static void forEachAndFilterDemo() {
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
