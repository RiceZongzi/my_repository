1、抽象类中的抽象方法（其前有abstract修饰）不能用private、static、synchronized、native访问修饰符修饰。

原因如下：

- 抽象方法没有方法体，是用来被继承的，所以不能用private修饰；
- static修饰的方法可以通过类名来访问该方法（即该方法的方法体），抽象方法用static修饰没有意义；
- 使用synchronized关键字是为该方法加一个锁。而如果该关键字修饰的方法是static方法。则使用的锁就是class变量的锁。如果是修饰类方法。则用this变量锁。但是抽象类不能实例化对象，因为该方法不是在该抽象类中实现的。是在其子类实现的。所以。锁应该归其子类所有。所以。抽象方法也就不能用synchronized关键字修饰了；
- native，这个东西本身就和abstract冲突，他们都是方法的声明，只是一个吧方法实现移交给子类，另一个是移交给本地操作系统。如果同时出现，就相当于即把实现移交给子类，又把实现移交给本地操作系统，那到底谁来实现具体方法呢？



2、接口是一种特殊的抽象类，接口中的方法全部是抽象方法（但其前的abstract可以省略），所以抽象类中的抽象方法不能用的访问修饰符这里也不能用。而且protected访问修饰符也不能使用，因为接口可以让所有的类去实现（非继承），不只是其子类，但是要用public去修饰。接口可以去继承一个已有的接口。

 

**[类、方法、成员变量和局部变量的可用修饰符](http://www.blogjava.net/keweibo/articles/150157.html)**

| **修饰符**             | **类** | **成员访求** | **构造方法** | **成员变量** | **局部变量** |
| ---------------------- | ------ | ------------ | ------------ | ------------ | ------------ |
| abstract（抽象的）     | √      | √            | －           | －           | －           |
| static （静态的）      | －     | √            | －           | √            | －           |
| public（公共的）       | √      | √            | √            | √            | －           |
| protected（受保护的）  |        | √            | √            | √            | －           |
| private（私有的）      | －     | √            | √            | √            | －           |
| synchronized（同步的） | －     | √            | －           | －           | －           |
| native（本地的）       | －     | √            | －           | －           | －           |
| transient（暂时的）    | －     | －           | －           | √            | －           |
| volatie（易失的）      | －     | －           | －           | √            | －           |
| final（不要改变的）    | √      | √            | －           | √            | √            |

 

| **类 修饰符**         |                                    |
| --------------------- | ---------------------------------- |
| Public                | 可以从其他类中访问                 |
| Abstract              | 本类不能被实例化                   |
| Final                 | 不能再声明子类                     |
| **构造函数修饰符**    |                                    |
| Public                | 可以从所有的类中访问               |
| Protected             | 只能从自己的类和它的子类中访问     |
| Private               | 只能在本类中访问                   |
| **域/成员变量修饰符** |                                    |
| Public                | 可以从所有的类中访问               |
| Protected             | 只能从本类和它的子类中访问         |
| Private               | 只能从本类中访问它                 |
| Static                | 对该类的所有实例只能有一个域值存在 |
| transient             | 不是一个对象持久状态的一部份       |
| Volatile              | 可以被异步的线程所修改             |
| final                 | 必须对它赋予初值并且不能修改它     |
| **局部变量 修饰符**   |                                    |
| final                 | 必须对它赋予初值并且不能修改它     |
| **方法修饰符**        |                                    |
| Public                | 可以从所有的类中访问它             |
| Protected             | 只能从本类及其子类中访问它         |
| Private               | 只能从本类中访问它                 |
| abstract              | 没有方法体，属于一个抽象类         |
| final                 | 子类不能覆盖它                     |
| static                | 被绑定于类本身而不是类的实例       |
| native                | 该方法由其他编程语言实现           |
| asnchronized          | 在一个线程调用它之前必须先给它加   |

**类的修饰符整合**

**一．类**

**类的修饰符：**

**Public**:可以在其他任何类中使用，默认为统一包下的任意类。

**Abstract:抽象类**，不能被实例化，可以包含抽象方法，抽象方法没有被实现，无具体功能，只能衍生子类。

**Final**:不能被继承。

**二．变量**

变量修饰符：

一个类的成员变量的声明必须在类体中，而不能在方法中，方法中声明的是**局部变量**。

- 可访问修饰符：
-  **static**：**类变量**：一个类所拥有的变量，不是类的每个实例有的变量。类变量是指不管类创建了多少对象，系统仅在第一次调用类的时候为类变量分配内存，所有对象共享该类的类变量，因此可以通过类本身或者某个对象来访问类变量。
- **final**：**常量**。
- **volatile**：声明一个可能同时被并存运行的几个线程所控制和修改的变量。

**实例变量**：和类变量对应，即每个对象都拥有各自独立的实例变量。

**三．方法：**（和变量对象分为实例方法和类方法，并用有无static修饰区别）

**类方法**：使用static关键字说明的方法

- 第一次调用含类方法的类是，系统只为该类创建一个版本，这个版本被该类和该类的所有实例共享。
- 类方法只能操作类变量，不能访问实例变量。类方法可以在类中被调用，不必创建实例来调用，当然也可以通过对象来调用。

**实例方法**：实例方法可以对当前对象的实例变量操作，而且可以访问类变量。

方法可以**重载**，要求：方法名相同，但是参数必须有区别。（参数不同可以使类型不同，顺序不同，个数不同）

方法的返回类型：若无返回类型，则声明为void.

方法中的变量作用域：

- 成员变量：整个类。
- 局部变量：定义起到方法块结束为止。
- 方法参数：整个方法或者构造方法。
- 异常处理参数：参数传递给异常处理方法。

**构造方法**：和类同名的方法。为新建对象开辟内存空间后，用于初始化新建的对象。不能用对象显式的调用。

**静态初始化器**：格式：static{<赋值语句组>}

静态初始化器与构造方法的区别： 

| 静态初始化器               | 构造方法           |
| -------------------------- | ------------------ |
| 对类的静态域初始化         | 对新建的对象初始化 |
| 类进入内存后，系统调用执行 | 执行new后自动执行  |
| 属特殊语句（仅执行一次）   | 属特殊方法         |

**方法的修饰符：**

**抽象方法：**用abstract修饰，只有声明部分，方法体为空，具体在子类中完成。

**类方法：**静态方法，用static修饰，

- 调用时，使用类名作为前缀，而不是类的某个实例对象名
- 不能被单独对象拥有，属于整个类共享。
- 不能处理成员变量。

**最终方法**：用final修饰，不能被子类重新定义的方法。

**本地方法**：用native修饰的方法，表示用其他语言书写的特殊方法，包括C，C++，FORTRAN，汇编语言等。

**四．类成员的访问控制符**：

即类的方法和成员变量的访问控制符，一个类作为整体对象不可见，并不代表他的所有域和方法也对程序其他部分不可见，需要有他们的访问修饰符判断。

权限如下： 

| 访问修饰符 | 同一个类 | 同包 | 不同包，子类 | 不同包，非子类 |
| ---------- | -------- | ---- | ------------ | -------------- |
| private    | √        |      |              |                |
| protected  | √        | √    | √            |                |
| public     | √        | √    | √            | √              |
| 默认       | √        | √    |              |                |