package com.springdata.jpa;

import com.springdata.jpa.dao.CustomerDao;
import com.springdata.jpa.dao.LinkManDao;
import com.springdata.jpa.entity.Customer;
import com.springdata.jpa.entity.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author ：by wgz
 * @description：
 * @Date ：Created in 2020/9/19 15:52
 * @Version: $
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToMany {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    /**
     * 会有一条多余的update语句
     *      * 由于一的一方可以维护外键：会发送update语句
     *      * 解决此问题：只需要在一的一方放弃维护权即可
     *
     */

    @Test
    @Transactional
    @Rollback(value = false)
    public void testAdd(){
        Customer customer = new Customer();
        LinkMan linkMan = new LinkMan();

        customer.setCustName("Valve");
        linkMan.setLkmName("Gabe");

        /*
         * 配置了客户到联系人的关系
         *      从客户的角度上：发送两条insert语句，发送一条更新语句更新数据库（更新外键）
         * 由于我们配置了客户到联系人的关系：客户可以对外键进行维护
         */
        customer.getLinkMans().add(linkMan);

        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testAdd1(){
        Customer customer = new Customer();
        LinkMan linkMan = new LinkMan();

        customer.setCustName("Valve");
        linkMan.setLkmName("Gabe");

        /*
         * 配置联系人到客户的关系（多对一）
         *    只发送了两条insert语句
         * 由于配置了联系人到客户的映射关系（多对一）
         */
        linkMan.setCustomer(customer);

        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    /**
     * 级联添加：保存一个客户的同时，保存客户的所有联系人
     *      需要在操作主体的实体类上，配置casacde属性
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCascadeAdd() {
        Customer customer = new Customer();
        customer.setCustName("MicroSoft");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("Bill");

        linkMan.setCustomer(customer);
        customer.getLinkMans().add(linkMan);

        customerDao.save(customer);
    }

    /**
     * 级联删除：
     *      删除1号客户的同时，删除1号客户的所有联系人
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCascadeRemove() {
        //1.查询1号客户
        Customer customer = customerDao.findOne(1L);
        //2.删除1号客户
        customerDao.delete(customer);
    }
}
