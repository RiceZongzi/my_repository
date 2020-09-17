package com.springdata.jpa;

import com.springdata.jpa.dao.CustomerDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpqlTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindJPQL() {
        System.out.println(customerDao.findJpql("马中原"));
    }

    @Test
    public void testFindSQL() {
        customerDao.findSql("马中原").forEach(objects -> System.out.println(objects));
    }
}
