[TOC]

# Lambda表达式
− Lambda 允许把函数作为一个方法的参数（函数作为参数传递到方法中）。
## 语法
(parameters) -> expression
或
(parameters) ->{ statements; }
以下是lambda表达式的重要特征:
-可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
-可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。
-可选的大括号：如果主体包含了一个语句，就不需要使用大括号。
-可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值
- Lambda 允许把函数作为一个方法的参数（函数作为参数传递到方法中）。

# Stream API
- 新添加的Stream API（java.util.stream） 把真正的函数式编程风格引入到Java中。

# 方法引用
- 方法引用提供了非常有用的语法，可以直接引用已有Java类或对象（实例）的方法或构造器。与lambda联合使用，方法引用可以使语言的构造更紧凑简洁，减少冗余代码。

# 默认方法
- 默认方法就是一个在接口里面有了一个实现的方法。

# 新工具
- 新的编译工具，如：Nashorn引擎 jjs、 类依赖分析器jdeps。

# Date Time API 
- 加强对日期与时间的处理。

# Optional 类 
- Optional 类已经成为 Java 8 类库的一部分，用来解决空指针异常。

# Nashorn, JavaScript 引擎 
- Java 8提供了一个新的Nashorn javascript引擎，它允许我们在JVM上运行特定的javascript应用。

从 JDK 1.8 开始，Nashorn取代Rhino(JDK 1.6, JDK1.7) 成为 Java 的嵌入式 JavaScript 引擎。Nashorn 完全支持 ECMAScript 5.1 规范以及一些扩展。
它使用基于 JSR 292 的新语言特性，其中包含在 JDK 7 中引入的 invokedynamic，将 JavaScript 编译成 Java 字节码。与先前的 Rhino 实现相比，这带来了 2 到 10倍的性能提升。
Nashorn JavaScript Engine 在 Java 15 已经不可用了。
