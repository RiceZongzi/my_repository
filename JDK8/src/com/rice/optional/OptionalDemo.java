package com.rice.optional;

import java.util.Optional;

/**
 * @author wgz
 * create date  2020/10/29 20:05
 */
public class OptionalDemo {

    /*
    Optional容器类的常用方法:
        Optional.of(T t) : 创建一个Optional实例
        Optional.empty() : 创建一个空的Optional实例
        Optional.ofNullable(T t): 若t不为null，创建Optional实例，否则创建空实例

        get() ： 如果在这个Optional中包含这个值，返回值，否则抛出异常：NoSuchElementException
        isPresent() : 判断是否包含值
        ifPresent(Consumer<? super T> consumer) : 如果值存在则使用该值调用 consumer，否则不做任何事情。
        orElse(T t) : 如果调用对象包含值，返回该值，否则返回t
        orElseGet(Supplier s) : 如果调用对象包含值，返回该值，否则返回s获取的值
        map(Function f) : 如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
        flatMap(Function mapper) : 与 map类似，要求返回值必须是Optional
    */
    public static void main(String[] args) {
//        construction();
//        test01(null);
//        test01(new Student("Axe", "Dire", 40));
//        test02(null);
//        test02(new Student("Axe", "Dire", 40));
        test03(null);
        test03(new Student("Axe", "Dire", 40));
    }

    private static void test03(Student student) {
        Optional<Student> ofNullableStudent = Optional.ofNullable(student);
        // 如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
        Optional<String> optionalMapName = ofNullableStudent.map(s -> s.getName());
        // get() 操作前线判断是否有值
        if (optionalMapName.isPresent()) {
            System.out.println(optionalMapName.get());
        }
        // 与上面类似，要求返回值必须是Optional
        Optional<String> optionalFlatMapName = ofNullableStudent.flatMap(s -> Optional.of(s.getName()));
        // get() 操作前线判断是否有值
        if (optionalFlatMapName.isPresent()) {
            System.out.println(optionalFlatMapName.get());
        }
    }

    private static void test02(Student student) {
        Optional<Student> ofNullableStudent = Optional.ofNullable(student);
        // 如果有值，返回传入的值；否则返回默认值
        Student orElseStudent = ofNullableStudent.orElse(
                new Student("AntiMage", "Radiant", 22));
        System.out.println(orElseStudent);

        // 函数式接口
        Student orElseGetStudent = ofNullableStudent.orElseGet(
                () -> new Student("AntiMage", "Radiant", 22));
        System.out.println(orElseGetStudent);
    }

    private static void test01(Student student) {
        Optional<Student> ofNullableStudent = Optional.ofNullable(student);

        // 如果有值，则打印；否则不做任何事情。
        if (ofNullableStudent.isPresent()) {
            System.out.println(ofNullableStudent.get());
        }
        // 等效于上面三行代码
        ofNullableStudent.ifPresent(System.out::println);
    }

    private static void construction() {

        // Optional.of(T t)，如果传入的t为null，直接抛出NullPointerException
        Optional<Student> ofStudent = Optional.of(new Student());
        System.out.println(ofStudent.get());

        // Optional.empty()
        Optional<Student> empty = Optional.empty();
        // NoSuchElementException
//        System.out.println(empty.get());

        // Optional.ofNullable(T t)，允许传入t为null
        Optional<Student> ofNullableStudent = Optional.ofNullable(new Student());
        System.out.println(ofNullableStudent.get());
    }
}

class Student {

    private String name;

    private String address;

    private int age;

    public Student(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
