package com.rice.jpa;

import com.rice.jpa.entity.Customer;
import com.rice.jpa.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class JpaText {

    /**
     * 测试jpa的保存
     *      案例：保存一个客户到数据库中
     *  Jpa的操作步骤
     *     1.加载配置文件创建工厂（实体管理器工厂）对象
     *     2.通过实体管理器工厂获取实体管理器
     *          persist     保存
     *          merge       更新
     *          remove      删除
     *          find/getReference     根据id查询
     *     3.获取事务对象，开启事务
     *          begin       开启事务
     * 			commit      提交事务
     * 			rollback    回滚
     *     4.完成增删改查操作
     *     5.提交事务（回滚事务）
     *     6.释放资源
     */

    /**
     * 测试更新
     */
    @Test
    public void testMerge(){
        // 获取实体管理器对象
        EntityManager entityManager = JpaUtils.getEntityManager();
        // 获取事务对象
        EntityTransaction transaction = entityManager.getTransaction();
        // 开启事务
        transaction.begin();
        // merge方法的构造，要传入一个实体对象，所以先查，再删
        Customer customer = entityManager.getReference(Customer.class, 14L);
        customer.setCustIndustry("运维");
        entityManager.merge(customer);
        transaction.commit();
        // 关闭资源
        entityManager.close();
    }

    /**
     * 测试删除
     */
    @Test
    public void testRemove(){
        // 获取实体管理器对象
        EntityManager entityManager = JpaUtils.getEntityManager();
        // 获取事务对象
        EntityTransaction transaction = entityManager.getTransaction();
        // 开启事务
        transaction.begin();
        // remove方法的构造，要传入一个实体对象，所以先查，再删
        entityManager.remove(entityManager.getReference(Customer.class, 12L));
        transaction.commit();
        // 关闭资源
        entityManager.close();
    }

    /**
     * 测试根据id查询
     *
     *  使用find方法查询：
     *      1.查询的对象就是当前客户对象本身
     *      2.在调用find方法的时候，就会发送sql语句查询数据库
     *
     *  立即加载
     */
    @Test
    public void testFind(){
        // 获取实体管理器对象
        EntityManager entityManager = JpaUtils.getEntityManager();
        // 获取事务对象
        EntityTransaction transaction = entityManager.getTransaction();
        // 开启事务
        transaction.begin();
        /*
         * find : 根据id查询数据
         *      class：查询数据的结果需要包装的实体类类型的字节码
         *      id：查询的主键的取值
         */
        Customer customer = entityManager.find(Customer.class, 12L);
        System.out.println(customer);
        transaction.commit();
        // 关闭资源
        entityManager.close();
    }

    /**
     * 测试根据id查询
     *
     *      getReference方法
     *          1.获取的对象是一个动态代理对象
     *          2.调用getReference方法不会立即发送sql语句查询数据库
     *              * 当调用查询结果对象的时候，才会发送查询的sql语句：什么时候用，什么时候发送sql语句查询数据库
     *
     * 延迟加载（懒加载）
     *      * 得到的是一个动态代理对象
     *      * 什么时候用，什么使用才会查询
     */
    @Test
    public void testGetReference(){
        // 获取实体管理器对象
        EntityManager entityManager = JpaUtils.getEntityManager();
        // 获取事务对象
        EntityTransaction transaction = entityManager.getTransaction();
        // 开启事务
        transaction.begin();
        /*
         * getReference : 根据id查询数据
         *      class：查询数据的结果需要包装的实体类类型的字节码
         *      id：查询的主键的取值
         */
        Customer customer = entityManager.getReference(Customer.class, 12L);
        System.out.println(customer);
        transaction.commit();
        // 关闭资源
        entityManager.close();
    }

    @Test
    public void testSave()  {
//        //1.加载配置文件创建工厂（实体管理器工厂）对象
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
//        //2.通过实体管理器工厂获取实体管理器
//        EntityManager em = factory.createEntityManager();
        //从工具类里获取实体管理器
        EntityManager em = JpaUtils.getEntityManager();
        //3.获取事务对象，开启事务
        //获取事务对象
        EntityTransaction tx = em.getTransaction();
        //开启事务
        tx.begin();
        //4.完成增删改查操作：保存一个客户到数据库中
        Customer customer = new Customer();
        customer.setCustName("王岩");
        customer.setCustIndustry("IT");
        //保存操作
        em.persist(customer);
        //5.提交事务
        tx.commit();
        //6.释放资源
        em.close();
//        factory.close();
    }
}
