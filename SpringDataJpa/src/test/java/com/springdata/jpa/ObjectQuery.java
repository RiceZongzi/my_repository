package com.springdata.jpa;

import com.springdata.jpa.dao.CustomerDao;
import com.springdata.jpa.dao.LinkManDao;
import com.springdata.jpa.entity.Customer;
import com.springdata.jpa.entity.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * 从一查多，默认采用延迟加载
 * 从多查一。默认采用立即加载
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ObjectQuery {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    @Test
    @Transactional
    public void testQuery1() {
        Customer customer = customerDao.getOne(2L);
        // 查询id为2的客户下的所有联系人
        Set<LinkMan> linkMans = customer.getLinkMans();
        // 遍历打印
        linkMans.forEach(linkMan -> System.out.println(linkMan));
    }

    /**
     * 对象导航查询：
     *      默认使用的是延迟加载的形式查询的
     *          调用get方法并不会立即发送查询，而是在使用关联对象的时候才会差和讯
     *      延迟加载！
     * 修改配置，将延迟加载改为立即加载
     *      fetch，需要配置到多表映射关系的注解上 （不推荐使用）
     *
     */
    @Test
    @Transactional
    public void testQuery2() {
        Customer customer = customerDao.findOne(2L);
        // 查询id为2的客户下的所有联系人
        Set<LinkMan> linkMans = customer.getLinkMans();
        // 遍历打印
        System.out.println(linkMans.size());
    }


    /**
     * 从联系人对象导航查询他的所属客户
     *      * 默认 ： 立即加载
     *  延迟加载：
     *
     */
    @Test
    @Transactional
    public void testQuery3() {
        LinkMan linkMan = linkManDao.getOne(2L);
        //对象导航查询所属的客户
        Customer customer = linkMan.getCustomer();
        System.out.println(customer);
    }
}
