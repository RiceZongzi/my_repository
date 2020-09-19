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

    @Test
    @Transactional
    @Rollback(value = false)
    public void testAdd(){
        Customer customer = new Customer();
        LinkMan linkMan = new LinkMan();

        customer.setCustName("Valve");
        linkMan.setLkmName("Gabe");

        customer.getLinkMans().add(linkMan);

        customerDao.save(customer);
        linkManDao.save(linkMan);
    }
}
