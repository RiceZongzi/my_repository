package com.rice.nashron;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

/**
 * @author wgz
 * @description
 * @date 2020/10/30 11:07
 */
public class NashronDemo {

    private static ScriptEngine oldEngine;
    private static ScriptEngine newEngine;
    private static String FILE_SEPARATOR = System.getProperty("file.separator");

    static {
        // 通过脚本名称获取
        oldEngine = new ScriptEngineManager().getEngineByName("JavaScript");
        // 通过文件扩展名获取
//        oldEngine = new ScriptEngineManager().getEngineByExtension("js");
        // 通过MIME类型来获取
//        oldEngine = new ScriptEngineManager().getEngineByMimeType("text/javascript");

        newEngine = new ScriptEngineManager().getEngineByName("Nashorn");
    }

    public static void main(String[] args) throws ScriptException, FileNotFoundException, URISyntaxException, NoSuchMethodException {

//        hello();
//        javaCallJavascript();
//        javascriptCallJava();
//        extend("JavaIntArray.js");
//        extend("JavaList.js");
//        extend("LambdaAndStream.js");
//        extend("JavaExtends.js");
        compare("100 + (12.5 - 7) * 3/4");
    }

    /**
     *
     * @date 2020/10/30
     * @param strExpress String类型公式
     * @return java.lang.Double
     */
    public static Double getResult(String strExpress){
        Double number = null;
        try{
            number = Double.valueOf(newEngine.eval(strExpress).toString());
            // is Not a Number
            if (Double.isNaN(number)) {
                number = 0.0;
                // ±∞
            } else if (Double.isInfinite(number)) {
                number = 0.0;
            }
        }catch(Exception t){
            t.printStackTrace();
            System.err.println("字符串型公式有误，请检查！");
        }
        return number;
    }

    private static void compare(String express) {
        Double oldNum = null;
        Double newNum = null;
        try {
            long beginOld = System.currentTimeMillis();
            oldNum = Double.valueOf(oldEngine.eval(express).toString());
            long afterOld = System.currentTimeMillis();
            newNum = Double.valueOf(newEngine.eval(express).toString());
            long afterNew = System.currentTimeMillis();
            // 统计耗时
            System.out.println("Old: " + (afterOld - beginOld) + " milliseconds and result：" + oldNum);
            System.out.println("New: " + (afterNew - afterOld) + " milliseconds and result：" + newNum);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("字符串型公式有误，请检查！");
        }
    }

    /**
     * @author wgz
     * @date 2020/10/30
     * 数组
     *     JavaScript的原生数组是无类型的
     *     Nashron允许在JavaScript中使用Java的类型数组
     *     int[]数组就像真实的Java整数数组那样
     *     我们试图向数组添加非整数时，Nashron在背后执行了一些隐式的转换，字符串会自动转换为整数
     * 集合
     *     可以使用任何Java集合
     *     首先需要通过Java.type定义Java类型，之后创建新的实例
     *     为了迭代集合和数组，Nashron引入了for each语句它，就像Java的范围遍历那样工作。
     *     一些类似java.util的包可以不使用java.type或JavaImporter直接访问
     * Lambda和Stream
     *     虽然ECMAScript 5.1没有Java8 lmbda表达式的简化箭头语法，
     *     但是可以在任何接受lambda表达式的地方使用函数字面值。
     * Extends
     *     Java类型可以由Java.extend轻易扩展。使用线程创建作为例子。
     * 等等。
     * 参考：https://www.jianshu.com/p/467aaf5254f8
     *
     * @throws URISyntaxException
     * @throws FileNotFoundException
     * @throws ScriptException
     */
    private static void extend(String filename) throws URISyntaxException,
            FileNotFoundException, ScriptException {
        newEngine.eval(new FileReader(String.valueOf(NashronDemo.class
                .getResource("js" + FILE_SEPARATOR + filename)
                .toURI().getPath())));
    }

    /**
     * 在JavaScript中调用Java函数
     * @author wgz
     * @date 2020/10/30
     * @throws URISyntaxException
     * @throws FileNotFoundException
     * @throws ScriptException
     */
    private static void javascriptCallJava() throws URISyntaxException,
            FileNotFoundException, ScriptException {
        newEngine.eval(new FileReader(String.valueOf(NashronDemo.class
                .getResource("js" + FILE_SEPARATOR + "JavaScriptCallJava.js")
                .toURI().getPath())));
    }

    /**
     * Java类可以通过Java.typeAPI扩展在JavaScript中引用。
     * 它就和Java代码中的import类似。
     * 只要定义了Java类型，我们就可以自然地调用静态方法welcome(String name)，
     * 然后像sout打印信息。由于方法是静态的，我们不需要首先创建实例。
     */
    public static String welcome(String name) {
        System.out.format("Hi there from Java, %s\n", name);
        return "Greetings from java!";
    }

    /**
     * JavaScript原始类型转换为合适的Java包装类，而JavaScript原生对象会使用内部的适配器类来表示。
     * 要记住jdk.nashorn.internal中的类可能会有所变化，所以不应该在客户端面向这些类来编程。
     */
    public static void javaClass (Object object) {
        System.out.println(object.getClass());
    }

    /**
     * 在向Java传递原生JavaScript对象时，可以使用ScriptObjectMirror类，
     * 它实际上是底层JavaScript对象的Java表示。
     * ScriptObjectMirror实现了Map接口，位于jdk.nashorn.api中。
     * 这个包中的类可以用于客户端代码。
     */
    public static void javaClass2 (ScriptObjectMirror mirror) {
        System.out.println(mirror.getClassName() + ": " +
                Arrays.toString(mirror.getOwnKeys(true)));
    }

    /**
     * 在Java中调用JavaScript函数
     * @author wgz
     * @date 2020/10/30
     * @throws ScriptException
     * @throws FileNotFoundException
     * @throws URISyntaxException
     * @throws NoSuchMethodException
     */
    private static void javaCallJavascript() throws ScriptException, FileNotFoundException,
            URISyntaxException, NoSuchMethodException {
        newEngine.eval(new FileReader(String.valueOf(NashronDemo.class
                .getResource("js" + FILE_SEPARATOR + "JavaCallJavaScript.js")
                .toURI().getPath())));
        // 为了调用函数，首先需要将脚本引擎转换为Invocable
        // Invocable接口由NashornScriptEngine实现，
        // 并且定义了invokeFunction方法来调用指定名称的JavaScript函数。
        Invocable invocable = (Invocable) newEngine;
        // 第一个参数为Javascript函数名，第二个参数为传入参数
        // Jdk8的Nashorn是基于ES5.1的，不支持let和const
        // Jdk9的Nashorn是基于ES6的。
        Object result = invocable.invokeFunction("welcome", "Rice");
        System.out.println(result);
        // 返回 class java.lang.String
        System.out.println(result.getClass());
        // Java对象在传入时不会在JavaScript端损失任何类型信息。
        // 由于脚本在JVM上原生运行，
        // 我们可以在Nashron上使用Java API或外部库的全部功能。
        // 返回 object java.time.LocalDateTime
        System.out.println(invocable.invokeFunction("jsClass", LocalDateTime.now()));
        // 返回 object java.util.Date
        System.out.println(invocable.invokeFunction("jsClass", new Date()));
        // 返回 object [I
        System.out.println(invocable.invokeFunction("jsClass", new int[]{}));
        // 返回 object java.util.Optional
        System.out.println(invocable.invokeFunction("jsClass", Optional.empty()));
    }

    private static void hello() throws ScriptException, FileNotFoundException, URISyntaxException {
        long beginOld = System.currentTimeMillis();
        oldEngine.eval("print('Hello World!');");
        long afterOld = System.currentTimeMillis();
        newEngine.eval("print('Hello World!');");
        long afterNew = System.currentTimeMillis();
        // 统计耗时
        System.out.println("Old: " + (afterOld - beginOld) + " milliseconds");
        System.out.println("New: " + (afterNew - afterOld) + " milliseconds");
        newEngine.eval(new FileReader(String.valueOf(NashronDemo.class
                // 跨平台使用，操作系统不同，分隔符不同
                .getResource("js" + FILE_SEPARATOR + "hello.js")
                // 路径中有 %20 等的时候的处理方式，是空格中文等，引起的
                .toURI().getPath())));
    }
}
