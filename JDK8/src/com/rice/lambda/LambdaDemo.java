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

    Lambda表达式省略规则:
        是可推导,可以省略
        凡是根据上下文推导出来的内容,都可以省略书写
        可以省略的内容:
            1.(参数列表):括号中参数列表的数据类型,可以省略不写
            2.(参数列表):括号中的参数如果只有一个,那么类型和()都可以省略
            3.{一些代码}:如果{}中的代码只有一行,无论是否有返回值,都可以省略({},return,分号)
        注意:要省略{},return,分号必须一起省略

    Lambda表达式使用注意
        使用Lambda必须具有接口，且要求接口中有且仅有一个抽象方法。
            无论是JDK内置的Runnable 、Comparator 接口还是自定义的接口，
            只有当接口中的抽象方法存在且唯一时，才可以使用Lambda。
        使用Lambda必须具有上下文推断。
            也就是方法的参数或局部变量类型必须为Lambda对应的接口类型，
            才能使用Lambda作为该接口的实例。
        备注：有且仅有一个抽象方法的接口，称为“函数式接口”。
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
