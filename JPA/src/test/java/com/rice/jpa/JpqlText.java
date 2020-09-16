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

}
