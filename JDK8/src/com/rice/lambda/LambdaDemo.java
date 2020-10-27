package com.rice.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author wgz
 * @description
 * @date 2020/10/27 10:56
 */
public class LambdaDemo {

    /**
    Lambda表达式的标准格式:
        由三部分组成:
            a.一些参数
            b.一个箭头
            c.一段代码
        格式:
            (参数列表) -> {一些重写方法的代码};
        解释说明格式:
            ():接口中抽象方法的参数列表,没有参数,就空着;有参数就写出参数,多个参数使用逗号分隔
            ->:传递的意思,把参数传递给方法体{}
            {}:重写接口的抽象方法的方法体
    */
    public static void main(String[] args) {
//        showDiffByWaysToCreateThread();
        sortObjectByCollections();
        sortObjectByArrays();
    }

    private static void sortObjectByArrays() {
        Person[] arr = {
                new Person("AntiMage", 26),
                new Person("Viper", 46),
                new Person("Invoker", 22),
                new Person("Axe", 30),
                new Person("Luna", 21)
        };
        // 匿名内部类
        Arrays.sort(arr, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        // Lambda @Since JDK 8
        Arrays.sort(arr, (o1, o2) -> o1.getAge() - o2.getAge());
        // Functional @Since JDK 8
        Arrays.sort(arr, Comparator.comparingInt(Person::getAge));
        for (Person person : arr) {
            System.out.println(person);
        }
    }

    private static void sortObjectByCollections() {
        ArrayList<Person> arrayList = new ArrayList<>();
        arrayList.add(new Person("AntiMage", 26));
        arrayList.add(new Person("Viper", 46));
        arrayList.add(new Person("Invoker", 22));
        arrayList.add(new Person("Axe", 30));
        arrayList.add(new Person("Luna", 21));
        // 匿名内部类
        arrayList.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        // Lambda @Since JDK 8
        arrayList.sort((o1, o2) -> o1.getAge() - o2.getAge());
        // Functional @Since JDK 8
        arrayList.sort(Comparator.comparingInt(Person::getAge));
        System.out.println(arrayList);
    }

    private static void showDiffByWaysToCreateThread() {
//        By implements Runnable interface
        // 创建Runnable接口的实现类对象
        RunnableExample runnableExample = new RunnableExample();
        // 创建Thread类对象,构造方法中传递Runnable接口的实现类
        Thread thread = new Thread(runnableExample);
        // 调用start方法开启新线程,执行run方法
        thread.start();

//        By anonymous inner class
        Runnable r = new Runnable(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " Anonymous inner class 线程创建了");
            }
        };
        new Thread(r).start();
//        Or less code
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " Anonymous inner class 线程创建了");
            }
        }).start();

//        By lambda expression
        new Thread(() -> System.out.println(Thread.currentThread().getName() + " Lambda expression 线程创建了")).start();
    }
}

class Person {
    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
