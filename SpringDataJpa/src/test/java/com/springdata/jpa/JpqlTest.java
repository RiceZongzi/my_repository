package com.springdata.jpa;

import com.springdata.jpa.dao.CustomerDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpqlTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindJPQL() {
        System.out.println(customerDao.findJpql("李华"));
    }

    /**
     * 测试jpql的更新操作
     *  * springDataJpa中使用jpql完成 更新/删除操作
     *         * 需要手动添加事务的支持
     *         * 默认会执行结束之后，回滚事务
     *   @Rollback : 设置是否自动回滚
     *          false | true
     */
    @Test
    @Transactional //添加事务的支持
    @Rollback(value = false)
    public void testUpdateCustomer() {
        customerDao.updateCustomer(3,"李华");
    }

    @Test
    public void testFindSQL() {
        List<Object[]> list = customerDao.findSql("马%");
        for(Object [] obj : list) {
            System.out.println(Arrays.toString(obj));
        }
    }

    @Test
    public void testIsNotNull() {
        customerDao.findByCustPhoneIsNotNull().forEach(customer -> System.out.println(customer));
    }

    @Test
    public void testIsNull() {
        customerDao.findByCustPhoneIsNull().forEach(customer -> System.out.println(customer));
    }
}
