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
        orElseGet(Supplier<? extends T> other) : 如果存在该值，返回值， 否则触发 other，并返回 other 调用的结果。
        orElseThrow(Supplier<? extends X> exceptionSupplier) ： 如果存在该值，返回包含的值，否则抛出由 Supplier 继承的异常
        map(Function<? super T,? extends U> mapper) : 如果有值，则对其执行调用映射函数得到返回值。如果返回值不为 null，则创建包含映射返回值的Optional作为map方法返回值，否则返回空Optional。
        flatMap(Function<? super T,Optional<U>> mapper) : 如果值存在，返回基于Optional包含的映射方法的值，否则返回一个空的Optional
        filter(Predicate<? super <T> predicate) : 如果值存在，并且这个值匹配给定的 predicate，返回一个Optional用以描述这个值，否则返回一个空的Optional。
    */
    public static void main(String[] args) {
//        construction();
//        test01(null);
//        test01(new Student("Axe", "Dire", 40));
//        test02(null);
//        test02(new Student("Axe", "Dire", 40));
//        test03(null);
//        test03(new Student("Axe", "Dire", 40));
        test04(null);
        test04(new Man());
        test04(new Man(new Goddess("Athena")));
        // Hera
        test05(Optional.ofNullable(null));
        // Hera
        test05(Optional.ofNullable(new OptionalMan()));
        // Athena
        test05(Optional.ofNullable(new OptionalMan(Optional.ofNullable((new Goddess("Athena"))))));
    }

    private static void test05(Optional<OptionalMan> man) {
        System.out.println(getOptionalGoddessName(man));
    }

    private static void test04(Man man) {
        System.out.println(getGoddessName(man));
    }

    private static String getGoddessName(Man man) {
        if (man != null) {
            Goddess goddess = man.getGoddess();
            if (goddess != null) {
                return goddess.getName();
            }
        }
        return "Hera";
    }

    private static String getOptionalGoddessName(Optional<OptionalMan> man) {
        return man.orElse(new OptionalMan())
                .getGoddess()
                .orElse(new Goddess("Hera"))
                .getName();
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

class Man {
    private Goddess goddess;

    public Goddess getGoddess() {
        return goddess;
    }

    public void setGoddess(Goddess goddess) {
        this.goddess = goddess;
    }

    public Man(Goddess goddess) {
        this.goddess = goddess;
    }

    public Man() {
    }

    @Override
    public String toString() {
        return "Man{" +
                "goddess=" + goddess +
                '}';
    }
}

class OptionalMan {
    private Optional<Goddess> goddess = Optional.empty();

    public Optional<Goddess> getGoddess() {
        return goddess;
    }

    public void setGoddess(Optional<Goddess> goddess) {
        this.goddess = goddess;
    }

    public OptionalMan(Optional<Goddess> goddess) {
        this.goddess = goddess;
    }

    public OptionalMan() {
    }

    @Override
    public String toString() {
        return "OptionalMan{" +
                "goddess=" + goddess +
                '}';
    }
}

class Goddess {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Goddess(String name) {
        this.name = name;
    }

    public Goddess() {
    }

    @Override
    public String toString() {
        return "Goddess{" +
                "name='" + name + '\'' +
                '}';
    }
}
