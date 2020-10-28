package com.rice.methodreference;

/**
 * @author wgz
 * @description
 * @date 2020/10/28 15:30
 */
public class MethodRefDemo {

    /**
     * 双冒号:: 为引用运算符，而它所在的表达式被称为方法引用。
     *      如果Lambda要表达的函数方案已经存在于某个方法的实现中，
     *      那么则可以通过双冒号来引用该方法作为Lambda的替代者。
     */
    public static void main(String[] args) {
//        methodReference();
//        objectMethodReference();
//        staticMethodReference();
        constructorMethodReference();
    }

    /**
     * 类的构造器(构造方法)引用
     */
    private static void constructorMethodReference() {
        // 调用printName方法
        // 方法的参数PersonBuilder接口是一个函数式接口
        // 可以传递Lambda
        printName("AntiMage",(String name)->{
            return new Person(name);
        });

        /*
            使用方法引用优化Lambda表达式
            构造方法new Person(String name) 已知
            创建对象已知 new
            就可以使用Person引用new创建对象
         */
        printName("AntiMage", Person::new);
    }

    /**
     *     通过类名引用静态成员方法
     *     类已经存在,静态成员方法也已经存在
     *     就可以通过类名直接引用静态成员方法
     */
    private static void staticMethodReference() {
        // 调用method方法，
        // 传递计算绝对值得整数，
        // 和Lambda表达式
        int λ = method(-10, (n)->{
            // 对参数进行绝对值得计算并返回结果
            return Math.abs(n);
        });
        System.out.println(λ);

        /*
            使用方法引用优化Lambda表达式
            Math类是存在的
            abs计算绝对值的静态方法也是已经存在的
            所以我们可以直接通过类名引用静态方法
         */
        int num = method(-10, Math::abs);
        System.out.println(num);
    }

    private static void objectMethodReference() {
        // 调用printString方法，
        // 方法的参数Printable是一个函数式接口，
        // 所以可以传递Lambda表达式
        printString((s)->{
            // 创建MethodRerObject对象
            MethodRerObject obj = new MethodRerObject();
            // 调用MethodRerObject对象中的成员方法printUpperCaseString
            obj.printUpperCaseString(s);
        });

        /*
        分析:
            使用方法引用优化Lambda
            对象是已经存在的MethodRerObject
            成员方法也是已经存在的printUpperCaseString
            所以我们可以使用对象名引用成员方法
         */
        // 创建MethodRerObject对象
        MethodRerObject obj = new MethodRerObject();
        printString(obj::printUpperCaseString);
    }

    /**
     * System.out 对象中有一个重载的println(String) 方法恰好就是我们所需要的。
     * 那么对于printString 方法的函数式接口参数，对比下面两种写法，完全等效：
     *      Lambda表达式写法： s -> System.out.println(s);
     *      方法引用写法：     System.out::println;
     */
    private static void methodReference() {
        // 调用printString方法
        // 方法的参数Printable是一个函数式接口
        // 所以可以传递Lambda
        printString((s) -> {
            System.out.println(s);
        });

        /*
         * 分析:
                Lambda表达式的目的,打印参数传递的字符串
                把参数s,传递给了System.out对象,调用out对象中的方法println对字符串进行了输出
                注意:
                    1.System.out对象是已经存在的
                    2.println方法也是已经存在的
                所以我们可以使用方法引用来优化Lambda表达式
                可以使用System.out方法直接引用(调用)println方法
         */
        printString(System.out::println);
    }

    /**
     * 定义一个方法，对字符串进行打印
     * @author wgz
     * @date 2020/10/28
     * @param p Printable接口
     */
    private static void printString(Printable p) {
        p.print("Hello World!");
    }

    /**
     *
     * @param number 要计算绝对值的整数
     * @param c 函数式接口Calcable
     */
    public static int method(int number, Calcable c){
        return c.calsAbs(number);
    }

    /**
     * @author wgz
     * create date 2020/10/28 23:37
     * @param name 姓名
     * @param pb PersonBuilder接口
     */
    public static void printName(String name, PersonBuilder pb){
        Person person = pb.builderPerson(name);
        System.out.println(person.getName());
    }
}



/**
 * 定义一个打印的函数式接口
 */
@FunctionalInterface
interface Printable {
    /**
     * 定义字符串的抽象方法
     * @author wgz
     * @date 2020/10/28
     * @param s 要进行打印操作的字符串
     */
    void print(String s);
}

class MethodRerObject {
    /**
     * 定义一个成员方法，把字符串按照大写输出
     * @author wgz
     * @date 2020/10/28
     * @param str 要进行打印操作的字符串
     */
    public void printUpperCaseString(String str){
        System.out.println(str.toUpperCase());
    }
}

@FunctionalInterface
interface Calcable {
    /**
     * 定义一个抽象方法，对整数进行绝对值计算并返回
     * @author wgz
     * @date 2020/10/28
     * @param number 整数
     * @return int
     */
    int calsAbs(int number);
}

/**
 * 定义父类
 */
class Human {
    /**
     * 定义一个sayHello的方法
     * @author wgz
     * @date 2020/10/28
     */
    public void sayHello(){
        System.out.println("Hello 我是Human!");
    }
}

/**
 * 定义子类
 */
class Man extends Human{
    @Override
    public void sayHello() {
        // 子类重写父类sayHello的方法
        System.out.println("Hello 我是Man!");
    }

    public void method(Greetable g){
        g.greet();
    }

    public void show(){
        // 调用method方法
        // 方法的参数Greetable是一个函数式接口
        // 所以可以传递Lambda
        method(()->{
            // 创建父类Human对象
            Human h = new Human();
            // 调用父类的sayHello方法
            h.sayHello();
        });

        // 因为有子父类关系
        // 所以存在的一个关键字super代表父类
        // 所以我们可以直接使用super调用父类的成员方法
        method(()->{
            super.sayHello();
        });

        /*
            使用super引用类的成员方法
            super是已经存在的
            父类的成员方法sayHello也是已经存在的
            所以我们可以直接使用super引用父类的成员方法
         */
        method(super::sayHello);
    }

    public static void main(String[] args) {
        new Man().show();
    }
}

/**
 * 定义见面的函数式接口
 */
@FunctionalInterface
interface Greetable {
    /**
     * 定义一个见面的方法
     * @author wgz
     * @date 2020/10/28
     */
    void greet();
}

/**
 * 定义一个富有的函数式接口
 */
@FunctionalInterface
interface Richable {
    /**
     * 定义一个想买什么就买什么的方法
     * @author wgz
     * @date 2020/10/28
     */
    void buy();
}

class Husband {
    public void buyHouse(){
        // 定义一个买房子的方法
        System.out.println("北京二环内买一套四合院!");
    }

    public void marry(Richable r){
        // 定义一个结婚的方法,参数传递Richable接口
        r.buy();
    }

    public void soHappy(){
        // 调用结婚的方法
        // 方法的参数Richable是一个函数式接口
        // 传递Lambda表达式
        marry(()->{
            // 使用this.成员方法，调用本类买房子的方法
            this.buyHouse();
        });

        /*
            使用方法引用优化Lambda表达式
            this是已经存在的
            本类的成员方法buyHouse也是已经存在的
            所以我们可以直接使用this引用本类的成员方法buyHouse
         */
        marry(this::buyHouse);
    }

    public static void main(String[] args) {
        new Husband().soHappy();
    }
}

class Person {
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/**
    定义一个创建Person对象的函数式接口
 */
@FunctionalInterface
interface PersonBuilder {
    Person builderPerson(String name);
}