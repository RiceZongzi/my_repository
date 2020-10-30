package com.rice.nashron;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;

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

    public static void main(String[] args) throws ScriptException, FileNotFoundException, URISyntaxException {

        hello();
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
