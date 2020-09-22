package com.rice.test;

import com.rice.SpringbootJpaApplication;
import com.rice.dao.UserDao;
import com.rice.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootJpaApplication.class)
public class JpaTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testAdd(){
        User user = new User();
        user.setName("Java");
        userDao.save(user);
    }
}
