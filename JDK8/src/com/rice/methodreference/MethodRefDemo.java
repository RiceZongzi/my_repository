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
//        printableDemo();
        printableObjectDemo();
    }

    private static void printableObjectDemo() {
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
    private static void printableDemo() {
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
