package com.springdata.jpa;

import com.springdata.jpa.dao.CustomerDao;
import com.springdata.jpa.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 根据id查询
     */
    @Test
    public void testFindOne(){
        System.out.println(customerDao.findOne(3L));
    }

    /**
     * save : 保存或者更新
     *      根据传递的对象是否存在主键id，
     *      如果没有id主键属性：保存
     *      存在id主键属性，根据id查询数据，更新数据
     */
    @Test
    public void testSave(){
        Customer customer = new Customer();
        customer.setCustName("李华");
        customer.setCustAddress("海淀");
        customer.setCustIndustry("Java");
        customer.setCustLevel("1");
        customerDao.save(customer);
    }

    @Test
    public void testUpdate(){
        Customer customer = new Customer();
        customer.setCustId(5L);
        customer.setCustIndustry("Python");
        customerDao.save(customer);
    }

    @Test
    public void testDelete(){
        customerDao.delete(5L);
    }

    /**
     * 查询所有
     */
    @Test
    public void testFindAll(){
        customerDao.findAll().forEach(customer -> System.out.println(customer));
    }

    /**
     * 统计数量
     */
    @Test
    public void testCount(){
        System.out.println(customerDao.count());
    }

    /**
     * 测试：判断id为4的客户是否存在
     *      1. 可以查询以下id为4的客户
     *          如果值为空，代表不存在，如果不为空，代表存在
     *      2. 判断数据库中id为4的客户的数量
     *          如果数量为0，代表不存在，如果大于0，代表存在
     */
    @Test
    public void testExists(){
        // 这个采用的是上面的方案2
        System.out.println(customerDao.exists(5L));
    }

    /**
     * 根据id从数据库查询
     *      @Transactional : 保证getOne正常运行
     *
     *  findOne：
     *      em.find()           :立即加载
     *  getOne：
     *      em.getReference     :延迟加载
     *      * 返回的是一个客户的动态代理对象
     *      * 什么时候用，什么时候查询
     */
    @Test
    @Transactional
    public void testGetOne(){
        System.out.println(customerDao.getOne(14L));
    }
}
