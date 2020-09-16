package com.springdata.jpa;

import com.springdata.jpa.dao.CustomerDao;
import com.springdata.jpa.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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
}
