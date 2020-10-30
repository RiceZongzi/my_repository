package com.rice.nashron;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
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
        javascriptCallJava();
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
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("Nashorn");
        engine.eval(new FileReader(String.valueOf(NashronDemo.class
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
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("Nashorn");
        engine.eval(new FileReader(String.valueOf(NashronDemo.class
                .getResource("js" + FILE_SEPARATOR + "JavaCallJavaScript.js")
                .toURI().getPath())));
        // 为了调用函数，首先需要将脚本引擎转换为Invocable
        // Invocable接口由NashornScriptEngine实现，
        // 并且定义了invokeFunction方法来调用指定名称的JavaScript函数。
        Invocable invocable = (Invocable) engine;
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
