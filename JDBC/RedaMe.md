
# 一
## 概念

- Java DataBase Connectivity  Java 数据库连接， Java语言操作数据库

## JDBC本质

* 官方（sun公司）定义的一套操作所有关系型数据库的规则，即接口。各个数据库厂商去实现这套接口，提供数据库驱动jar包。我们可以使用这套接口（JDBC）编程，真正执行的代码是驱动jar包中的实现类。
## 快速入门

### 步骤

- 导入驱动jar包 mysql-connector-java-5.1.37-bin.jar
  - 复制mysql-connector-java-5.1.37-bin.jar到项目的libs目录下
  - 右键-->Add As Library
- 注册驱动
- 获取数据库连接对象 Connection
- 定义sql
- 获取执行sql语句的对象 Statement
- 执行sql，接受返回结果
- 处理结果
- 释放资源

* 代码实现：
  	* 导入驱动jar包
   * 注册驱动
      *  `Class.forName("com.mysql.jdbc.Driver");`
   * 获取数据库连接对象
      * `Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db3", "root", "root");`
   * 定义sql语句
      * `String sql = "update account set balance = 500 where id = 1";`
   * 获取执行sql的对象 Statement
      * `Statement stmt = conn.createStatement();`
   * 执行sql
      * `int count = stmt.executeUpdate(sql);`
   * 处理结果
      * `System.out.println(count);`
   * 释放资源
      * `stmt.close();`
      * `conn.close();`

### 详解各个对象

#### DriverManager：驱动管理对象

* 注册驱动：告诉程序该使用哪一个数据库驱动jar
* 获取数据库连接：
  * 方法：`connection getConnection(String url, String user, String password) `
  * 参数：
  	* url：指定连接的路径
  		* 语法：`jdbc:mysql://ip地址(域名):端口号/数据库名称`
  		* 简写：如果连接的是本机mysql服务器，并且mysql服务默认端口是3306，则url可以简写为：`jdbc:mysql:///数据库名称`
  	* user：用户名
  	* password：密码 

#### Connection：数据库连接对象

- 获取执行sql 的对象
  - `Statement createStatement()`
  - `PreparedStatement prepareStatement(String sql)  `
- 管理事务：
  - 开启事务：`setAutoCommit(boolean autoCommit) `
    - 调用该方法设置参数为`false`，即开启事务
  - 提交事务：`commit() `
  - 回滚事务：`rollback() `

#### Statement：执行sql的对象

- 执行sql
  - `boolean execute(String sql) `
    - 可以执行任意的sql
  - `int executeUpdate(String sql) `
    - 执行DML（insert、update、delete）语句、DDL(create，alter、drop)语句
  - 返回值：
    - 影响的行数，可以通过这个影响的行数判断DML语句是否执行成功 
    - 返回值 > 0的则执行成功
    - 反之，则失败。
  - `ResultSet executeQuery(String sql)  `
    - 执行DQL（select)语句

#### ResultSet：结果集对象,封装查询结果

* `boolean next()`

  * 游标向下移动一行，判断当前行是否是最后一行末尾(是否有数据)
  * 如果是，则返回false
  * 如果不是，则返回true

* getXxx(参数):获取数据
	* Xxx：代表数据类型   如： int getInt() ,	String getString()
	* 参数：
		* int：代表列的编号,从1开始   如： getString(1)
		* String：代表列名称。 如： getDouble("balance")

* 使用步骤：
	
	* 游标向下移动一行
	* 判断是否有数据
	
* 获取数据
  
   * ```java
     //循环判断游标是否是最后一行末尾。
      while(rs.next()){
          //获取数据
          int id = rs.getInt(1);
          String name = rs.getString("name");
          double balance = rs.getDouble(3);
       System.out.println(id + "---" + name + "---" + balance);
      }
     ```

#### PreparedStatement：执行sql的对象

1. SQL注入问题：在拼接sql时，有一些sql的特殊关键字参与字符串的拼接。会造成安全性问题
	1. 输入用户随便，输入密码：a' or 'a' = 'a
	2. sql：select * from user where username = 'fhdsjkf' and password = 'a' or 'a' = 'a' 

2. 解决sql注入问题：使用PreparedStatement对象来解决
3. 预编译的SQL：参数使用?作为占位符
4. 步骤：
	1. 导入驱动jar包 mysql-connector-java-5.1.37-bin.jar
	2. 注册驱动
	3. 获取数据库连接对象 Connection
	4. 定义sql
		* 注意：sql的参数使用？作为占位符。 如：select * from user where username = ? and password = ?;
	5. 获取执行sql语句的对象 PreparedStatement  Connection.prepareStatement(String sql) 
	6. 给？赋值：
		* 方法： setXxx(参数1,参数2)
			* 参数1：？的位置编号 从1 开始
			* 参数2：？的值
	7. 执行sql，接受返回结果，不需要传递sql语句
	8. 处理结果
	9. 释放资源

5. 注意：后期都会使用PreparedStatement来完成增删改查的所有操作
	1. 可以防止SQL注入
	2. 效率更高




## JDBC控制事务
#### 事务

- 一个包含多个步骤的业务操作。如果这个业务操作被事务管理，则这多个步骤要么同时成功，要么同时失败。

#### 操作

- 开启事务
- 提交事务
- 回滚事务

#### 使用Connection对象来管理事务

* 开启事务：setAutoCommit(boolean autoCommit) ：调用该方法设置参数为false，即开启事务
	* 在执行sql之前开启事务
* 提交事务：commit() 
	* 当所有sql都执行完提交事务
* 回滚事务：rollback() 
	* 在catch中回滚事务


# 二

## 数据库连接池
### 概念

- 其实就是一个容器(集合)，存放数据库连接的容器。当系统初始化好后，容器被创建，容器中会申请一些连接对象，当用户来访问数据库时，从容器中获取连接对象，用户访问完之后，会将连接对象归还给容器。

### 好处

- 节约资源
- 用户访问高效

### 实现

- 标准接口：DataSource   javax.sql包下的
  - 方法
    - 获取连接：getConnection()
    - 归还连接：Connection.close()。如果连接对象Connection是从连接池中获取的，那么调用Connection.close()方法，则不会再关闭连接了，而是归还连接。
- 数据库厂商来实现
  - C3P0：数据库连接池技术
  - Druid：数据库连接池实现技术，由阿里巴巴提供的

#### C3P0

* 步骤：
	- 导入jar包 
	  - `c3p0-0.9.5.2.jar`
	  - `mchange-commons-java-0.2.12.jar `
	- 定义配置文件：
	  - 名称： `c3p0.properties` 或者 `c3p0-config.xml`
  - 路径：直接将文件放在`src`目录下即可。
	- 创建核心对象，数据库连接池对象 `ComboPooledDataSource`
	- 获取连接` getConnection`
* 代码：
	 * 创建数据库连接池对象
   	 * DataSource ds  = new ComboPooledDataSource();
   * 获取连接对象
   	 * Connection conn = ds.getConnection();

#### Druid

- 步骤：
  - 导入jar包 
    - druid-1.0.9.jar
  - 定义配置文件
    - 是properties形式的
    - 可以叫任意名称，可以放在任意目录下
  - 加载配置文件，Properties
  - 获取数据库连接池对象，通过工厂来来获取  DruidDataSourceFactory
  - 获取连接，getConnection

## SpringJDBC

Spring框架对JDBC的简单封装。提供了一个JDBCTemplate对象简化JDBC的开发

步骤：
- 导入jar包
- 创建JdbcTemplate对象。依赖于数据源DataSource
  - JdbcTemplate template = new JdbcTemplate(ds);
- 调用JdbcTemplate的方法来完成CRUD的操作
  - update():执行DML语句。增、删、改语句
  - queryForMap():查询结果将结果集封装为map集合，将列名作为key，将值作为value 将这条记录封装为一个map集合
    - 注意：这个方法查询的结果集长度只能是1
  - queryForList():查询结果将结果集封装为list集合
    - 注意：将每一条记录封装为一个Map集合，再将Map集合装载到List集合中
  - query():查询结果，将结果封装为JavaBean对象
    - query的参数：RowMapper
      - 一般我们使用BeanPropertyRowMapper实现类。可以完成数据到JavaBean的自动封装
      - new BeanPropertyRowMapper<类型>(类型.class)
  - queryForObject：查询结果，将结果封装为对象
    - 一般用于聚合函数的查询
