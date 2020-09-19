package com.springdata.jpa;

import com.springdata.jpa.dao.CustomerDao;
import com.springdata.jpa.dao.LinkManDao;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
}
