package com.rice.jpa;

import com.rice.jpa.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class JpqlText {
    /**
     * 查询全部
     *      jqpl：from com.rice.jpa.entity.Customer
     *      sql：SELECT * FROM cst_customer
     */
    @Test
    public void testFindAll() {
        //1.获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
        //创建Query查询对象，query对象才是执行jqpl的对象
        Query query = em.createQuery("from Customer ");
        //发送查询，并封装结果集
        query.getResultList().forEach(c -> System.out.println(c));
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     * 排序查询： 倒序查询全部客户（根据id倒序）
     *      sql：SELECT * FROM cst_customer ORDER BY cust_id DESC
     *      jpql：from Customer order by custId desc
     */
    @Test
    public void testOrders() {

    }

    /**
     * 使用jpql查询，统计客户的总数
     *      sql：SELECT COUNT(cust_id) FROM cst_customer
     *      jpql：select count(custId) from Customer
     */
    @Test
    public void testCount() {

    }

    /**
     * 分页查询
     *      sql：select * from cst_customer limit 0,2
     *      jqpl : from Customer
     */
    @Test
    public void testPaged() {
        //1.获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
        //创建Query查询对象，query对象才是执行jqpl的对象
        Query query = em.createQuery("from Customer ");
        //起始索引
        query.setFirstResult(0);
        //每页查询条数
        query.setMaxResults(2);
        //发送查询，并封装结果集
        query.getResultList().forEach(c -> System.out.println(c));
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     * 条件查询
     *     案例：查询客户名称以‘传智播客’开头的客户
     *          sql：SELECT * FROM cst_customer WHERE cust_name LIKE  ?
     *          jpql : from Customer where custName like ?
     */
    @Test
    public void testCondition() {
        //1.获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
        Query query = em.createQuery("from Customer where custName like ?");
        //第一个参数：占位符的索引位置（从1开始），第二个参数：取值
        query.setParameter(1,"马%");
        //发送查询，并封装结果集
        query.getResultList().forEach(c -> System.out.println(c));
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }
}
