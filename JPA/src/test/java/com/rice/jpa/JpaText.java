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
     *          find/getRefrence     根据id查询
     *     3.获取事务对象，开启事务
     *          begin       开启事务
     * 			commit      提交事务
     * 			rollback    回滚
     *     4.完成增删改查操作
     *     5.提交事务（回滚事务）
     *     6.释放资源
     */
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
        customer.setCustName("张铁");
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
