[TOC]

# 函数式接口
- 函数式接口(Functional Interface)就是一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口，函数式接口可以被隐式转换为 lambda 表达式。
```java
@FunctionalInterface
interface GreetingService 
{
    void sayMessage(String message);
}
```
# Lambda表达式
- Lambda 允许把函数作为一个方法的参数（函数作为参数传递到方法中）。

## 语法
```java
(parameters) -> expression
// 或者
(parameters) -> { 
    statements; 
}
```


以下是lambda表达式的重要特征:

- 可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
- 可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。
- 可选的大括号：如果主体包含了一个语句，就不需要使用大括号。
- 可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值

- Lambda 允许把函数作为一个方法的参数（函数作为参数传递到方法中）。

# Stream API
- 新添加的Stream API（java.util.stream） 把真正的函数式编程风格引入到Java中。

# 方法引用
- 方法引用提供了非常有用的语法，可以直接引用已有Java类或对象（实例）的方法或构造器。与lambda联合使用，方法引用可以使语言的构造更紧凑简洁，减少冗余代码。

# 默认方法
- 默认方法就是一个在接口里面有了一个实现的方法。简单说，默认方法就是接口可以有实现方法，而且不需要实现类去实现其方法。我们只需在方法名前面加个 default 关键字即可实现默认方法。

# Base64
- 在Java 8中，Base64编码已经成为Java类库的标准。内置了 Base64 编码的编码器和解码器。

  Base64工具类提供了一套静态方法获取下面三种BASE64编解码器：

  - **基本：**输出被映射到一组字符A-Za-z0-9+/，编码不添加任何行标，输出的解码仅支持A-Za-z0-9+/。
  - **URL：**输出映射到一组字符A-Za-z0-9+_，输出是URL和文件。
  - **MIME：**输出隐射到MIME友好格式。输出每行不超过76字符，并且使用'\r'并跟随'\n'作为分割。编码输出最后没有行分割。

# Date Time API 
- 加强对日期与时间的处理。

# Optional 类 
- Optional 类已经成为 Java 8 类库的一部分，用来解决空指针异常。

# Nashorn, JavaScript 引擎 
- `Java 8`提供了一个新的`Nashorn Javascript`引擎，它允许我们在`JVM`上运行特定的`Javascript`应用。`Nashorn`取代`Rhino`成为`Java`的嵌入式 `JavaScript`引擎。
- `Nashorn`完全支持`ECMAScript 5.1`规范以及一些扩展。
  它使用基于`JSR 292`的新语言特性，其中包含在 `Java 7`中引入的 `invokedynamic`，将`JavaScript`编译成`Java`字节码。与先前的`Rhino`实现相比，这带来了 2 到 10倍的性能提升。
- `Nashorn JavaScript Engine`在 `Java 15`已经不可用了。
