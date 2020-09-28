### Java Base

#### HashMap 和 HashTable 的区别

- 两者都可以用来存储 key-value 型数据

- HashMap
  - 可以把 null 当做 key 或者 value
  - 线程不安全，效率高
- HashTable
  - 不可以把 null 当做 key 或者 value
  - 线程安全，效率稍低

又想线程安全，又想效率高，又该如何？

- ConcurrentHashMap
  - 通过把整个 Map 分为 N 个 Segment，可以提供相同的线程安全，但是效率提升 N 倍，默认16



#### 实现一个拷贝文件的工具类使用字节流还是字符流？

- 字节流，考虑通用性



#### 设计模式

- 单例
  - 饱汉模式
  - 饥汉模式
- 工厂：Spring IOC
- 代理：Spring AOP 动态代理
- 包装



#### 线程的几种实现方式

- 继承 Thread 类
  - Java 中只支持单继承，造成扩展性不强
- 实现 Runnable 接口
- 启动线程使用 start() 方法，而启动以后执行的是 run() 方法。
- 通过 thread.setName("线程名"); ，在创建线程完成后，都需要设置线程名称。



#### 线程并发库

- java.util.current
- Java 通过 Executors 提供的四个静态方法创建线程池。
  - newCachedThreadPool
    - 可缓存，灵活回收空闲线程，超出则创建新的线程
  - newFixedThreadPool 
    - 定长，超出的线程在队列中等待
  - newScheduledThreadPool
    - 定长，定时、周期执行任务
  - newSingleThreadPool
    - 单线程化，所有任务按照指定顺序（优先级）执行
- 限定线程个数，不会因为线程过多导致系统运行异常
- 节约资源



### Web

#### HTTP Get 和 Post 请求的区别

- Get、Post、Put、Delete 分别对应着资源的查、改、增、删
- Get
  - 请求数据会在地址栏展示，? 分隔 url 和 数据，& 连接多个参数
  - 由于地址栏长度存在限制，导致 Get 请求传输的数据有限制
  - 由于请求数据放置于地址栏，可能会造成隐私泄露。
- Post
  - 请求数据不会再地址栏展示，而是放置在请求体中。
  - 没有限制



#### Jsp 和 Servlet 的相同点和不同点

- Jsp 是 Servlet 技术的扩展，所有的 Jsp 都会被翻译为一个继承 HttpServlet 的类，这个 Servlet 对外提供服务。
- Jsp
  - Java 和 HTML 可以组合成一个扩展名为 .jsp 的文件
  - 侧重于视图
  - 做界面展示比较方便，嵌入逻辑比较复杂
- Servlet
  - 应用逻辑是在 Java 文件中，完全和展示层的 HTML 里分离。
  - 侧重于逻辑
  - 如果要实现 HTML 功能，必须使用 Writer 输出对应的 HTML



#### Jsp 内置对象

- request
- response
- pageContext
- session
- application
- out
- config
- page
- exception



#### Jsp 作用域

- request
- pageContext
- application 
- session



#### 对 Servlet 的理解

- Java 编写的 服务端程序。
- 都要直接或间接的实现 Servlet 接口。
- 主要功能在于交互式的浏览修改数据，生成 Web 内容。
- 运行图支持 Java 的应用服务器中。



#### Servlet 生命周期

- Servlet 启动时，开始加载 Servlet 的 class 文件，生命周期开始。
- Servlet 被服务器实例化后，容器运行其 init() 方法，完成初始化。
- 请求到达时，运行其 service() 方法，自动运行请求对应的 doGet()/doPost()
- 服务器关闭，销毁实例，调用其 destroy() 方法，生命周期结束。



#### Servle API 中 forward() 与 redirect() 的区别

- forward ()，仅是容器中控制权的转向，浏览器不会显示出转向后的地址。
  - 服务端转向
  - 地址不改变
  - 一次请求
  - 效率较高
- redirect ()，完全跳转，浏览器会得到跳转的地址，并重新发送请求链接。
  - 客户端跳转
  - 地址改变
  - 重新发起请求
- 前者更加高效，在满足需要时，尽量使用前者，但如果需要跳转到其他服务器的资源，则必须使用 sendRedirect() 方法。



#### Session 和 Cookie 的区别

- 都是会话跟踪技术。
- Session
  - 存放在 Server 端
  - 访问增多，可能占用服务器性能
  - 将登陆等重要信息存放为 Session
- Cookie
  - 存放在 Client 端
  - 有安全隐患，而且单个 Cookie 保存的数据不能超过 4kb
  - 其他信息如有保留，可以放在 Cookie 中



#### MVC

- MVC
  - M -- Model 	模型，JavaBean
  - V -- View         视图，Html、FreeMarker
  - C -- Control    控制器，Servlet
- 最经典的MVC，Jsp + Servlet + JavaBean
  - 就是把视图和逻辑隔离开来
- SpringMVC、Struts2
  - Jsp + 核心控制器 + action + JavaBean



### DB

#### 数据库分类

- 关系型
  - MySQL、Oracle、SQLServer
- 非关系型
  - Redis、memcache、mogodb、hadoop



#### 关系型数据库的三范式

- 列数据的不可分割，数据库表的每一列都是不可分割的数据项
- 主键，数据库表的行必须可以被唯一的区分
- 外键，数据库表中不包含已在其他表中包含的非主关键字信息



#### 事务

- A，原子性，事务操作不可分割，要么全部成功，要么全部失败
- C，一致性，失败了要对前面的进行回滚
- I，隔离性，事务开始后，不受其他事务干扰
- D，持久性，事务开始了，就不能终止



#### 数据库分页

- MySql

  - limit offset,size

- Oracle

  - 三层嵌套

  - ```sql
    select * from (
        select ROWNUM rn, e.* from (
            select * from EMP order by SAL desc
        ) e where ROWNUM < 11
    ) where rn > 5;
    ```

