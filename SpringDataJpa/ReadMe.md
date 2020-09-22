# 一

## ORM思想

	主要目的：操作实体类就相当于操作数据库表
	建立两个映射关系：
		实体类和表的映射关系
		实体类中属性和表中字段的映射关系
	不再重点关注：sql语句
	
	实现了ORM思想的框架：mybatis，hibernate

## Hibernate框架介绍
	Hibernate是一个开放源代码的对象关系映射框架，
		它对JDBC进行了非常轻量级的对象封装，
		它将POJO与数据库表建立映射关系，是一个全自动的orm框架

## JPA规范
	Jpa规范，实现Jpa规范，内部是由接口和抽象类组成

## JPA的基本操作
### JPA操作的操作步骤

- 加载配置文件创建实体管理器工厂
  			Persisitence：静态方法(根据持久化单元名称创建实体管理器工厂)
        				createEntityMnagerFactory(持久化单元名称)
        			作用：创建实体管理器工厂
- 根据实体管理器工厂，创建实体管理器
			EntityManagerFactory ：获取EntityManager对象
			方法：createEntityManager
			* 内部维护的很多的内容
				内部维护了数据库信息，
				维护了缓存信息
				维护了所有的实体管理器对象
				再创建EntityManagerFactory的过程中会根据配置创建数据库表
			* EntityManagerFactory的创建过程比较浪费资源
			特点：线程安全的对象
				多个线程访问同一个EntityManagerFactory不会有线程安全问题
			* 如何解决EntityManagerFactory的创建过程浪费资源(耗时)的问题？
			思路：创建一个公共的EntityManagerFactory的对象
			* 静态代码块的形式创建EntityManagerFactory
- 创建事务对象，开启事务
  - EntityManager对象：实体类管理器
  - beginTransaction : 创建事务对象
  - presist ： 保存
  - merge  ： 更新
  - remove ： 删除
  - find/getRefrence ： 根据id查询
  - Transaction 对象 ： 事务
    - begin：开启事务
    - commit：提交事务
    - rollback：回滚
- 增删改查操作
- 提交事务
- 释放资源

### 搭建环境的过程

- 创建maven工程导入坐标
- 需要配置jpa的核心配置文件
    - 位置：配置到类路径下的一个叫做 META-INF 的文件夹下
    - 命名：persistence.xml
- 编写客户的实体类
- 配置实体类和表，类中属性和表中字段的映射关系
- 保存客户到数据库中
- 基本CRUD
    - persist ： 保存
    - merge ： 更新
    - remove ： 删除
    - find/getRefrence ： 根据id查询
        	
### jpql查询

​	`sql`：查询的是表和表中的字段
​	`jpql`：查询的是实体类和类中的属性
​	* jpql和sql语句的语法相似

# 二

## SpringDataJpa的概述

## SpringDataJpa的入门操作

### 基本CRUD

- 搭建环境
  - 创建工程导入坐标
  - 配置spring的配置文件(配置spring Data jpa的整合)
  - 编写实体类(Customer)，使用jpa注解配置映射关系
- 编写一个符合springDataJpa的dao层接口
  		* 只需要编写dao层接口，不需要编写dao层接口的实现类
    	 * dao层接口规范
        	 * 需要继承两个接口(JpaRepository，JpaSpecificationExecutor)
      * 需要提供响应的泛型
		* findOne(id) ：根据id查询
		* save(customer):保存或者更新(依据：传递的实体类对象中，是否包含id属性)
		* delete(id) ：根据id删除
		* findAll() : 查询全部

## SpringDataJpa的运行过程和原理剖析

- 通过JdkDynamicAopProxy的invoke方法创建了一个动态代理对象
- SimpleJpaRepository当中封装了JPA的操作(借助JPA的api完成数据库的CRUD)
- 通过hibernate完成数据库操作(封装了jdbc)

## 复杂查询

### 借助接口中的定义好的方法完成查询

- findOne(id):根据id查询

### JPQL的查询方式

```
jpql ： jpa query language  (jpq查询语言)
特点：语法或关键字和sql语句类似
	查询的是类和类中的属性
	需要将JPQL语句配置到接口方法上
```

- 特有的查询：需要在dao接口上配置方法
- 在新添加的方法上，使用注解的形式配置jpql查询语句
- 注解 ： @Query

### SQL语句的查询

- 特有的查询：需要在dao接口上配置方法
- 在新添加的方法上，使用注解的形式配置sql查询语句
- 注解 ： @Query
  - value ：jpql语句 | sql语句
  - nativeQuery ：false(使用jpql查询) | true(使用本地查询：sql查询)
    				是否使用本地查询

### 方法名称规则查询

# 三

## Specifications动态查询

	JpaSpecificationExecutor 方法列表
	
		T findOne(Specification<T> spec);  //查询单个对象
	
		List<T> findAll(Specification<T> spec);  //查询列表
	
		//查询全部，分页
		//pageable：分页参数
		//返回值：分页pageBean（page：是springdatajpa提供的）
		Page<T> findAll(Specification<T> spec, Pageable pageable);
	
		//查询列表
		//Sort：排序参数
		List<T> findAll(Specification<T> spec, Sort sort);
	
		long count(Specification<T> spec);//统计查询
		
	* Specification ：查询条件
		自定义我们自己的Specification实现类
			实现
				//root：查询的根对象（查询的任何属性都可以从根对象中获取）
				//CriteriaQuery：顶层查询对象，自定义查询方式（了解：一般不用）
				//CriteriaBuilder：查询的构造器，封装了很多的查询条件
				Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb); //封装查询条件

## 多表之间的关系和操作多表的操作步骤

	表关系
		一对一
		一对多：
			一的一方：主表
			多的一方：从表
			外键：需要再从表上新建一列作为外键，他的取值来源于主表的主键
		多对多：
			中间表：中间表中最少应该由两个字段组成，这两个字段做为外键指向两张表的主键，又组成了联合主键
	
	讲师对学员：一对多关系
			
	实体类中的关系
		包含关系：可以通过实体类中的包含关系描述表关系
		继承关系
	
	分析步骤
		1.明确表关系
		2.确定表关系（描述 外键|中间表）
		3.编写实体类，再实体类中描述表关系（包含关系）
		4.配置映射关系

## 完成多表操作

	i.一对多操作
		案例：客户和联系人的案例（一对多关系）
			客户：一家公司
			联系人：这家公司的员工
		
			一个客户可以具有多个联系人
			一个联系人从属于一家公司
			
		分析步骤
			1.明确表关系
				一对多关系
			2.确定表关系（描述 外键|中间表）
				主表：客户表
				从表：联系人表
					* 再从表上添加外键
			3.编写实体类，再实体类中描述表关系（包含关系）
				客户：再客户的实体类中包含一个联系人的集合
				联系人：在联系人的实体类中包含一个客户的对象
			4.配置映射关系
				* 使用jpa注解配置一对多映射关系
	
		级联：
			操作一个对象的同时操作他的关联对象
			
			级联操作：
				1.需要区分操作主体
				2.需要在操作主体的实体类上，添加级联属性（需要添加到多表映射关系的注解上）
				3.cascade（配置级联）
			
			级联添加，
				案例：当我保存一个客户的同时保存联系人
			级联删除
				案例：当我删除一个客户的同时删除此客户的所有联系人
				
	ii.多对多操作
		案例：用户和角色（多对多关系）
			用户：
			角色：
	
		分析步骤
			1.明确表关系
				多对多关系
			2.确定表关系（描述 外键|中间表）
				中间间表
			3.编写实体类，再实体类中描述表关系（包含关系）
				用户：包含角色的集合
				角色：包含用户的集合
			4.配置映射关系
			
	iii.多表的查询
		1.对象导航查询
			查询一个对象的同时，通过此对象查询他的关联对象
			
			案例：客户和联系人
			
			从一方查询多方
				* 默认：使用延迟加载（****）
				
			从多方查询一方
				* 默认：使用立即加载