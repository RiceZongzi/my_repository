package com.springdata.jpa;

import com.springdata.jpa.dao.RoleDao;
import com.springdata.jpa.dao.UserDao;
import com.springdata.jpa.entity.Role;
import com.springdata.jpa.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ManyToMany {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    /**
     * 保存一个用户，保存一个角色
     *
     *  多对多放弃维护权：被动的一方放弃
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testAdd() {
        User user = new User();
        user.setUserName("Jobs");

        Role role = new Role();
        role.setRoleName("PM");
        //配置用户到角色关系，可以对中间表中的数据进行维护
        user.getRoles().add(role);
        //配置角色到用户的关系，可以对中间表的数据进行维护
        role.getUsers().add(user);
        userDao.save(user);
        roleDao.save(role);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testCascadeAdd() {
        User user = new User();
        user.setUserName("Cook");

        Role role = new Role();
        role.setRoleName("COO");
        //配置用户到角色关系，可以对中间表中的数据进行维护
        user.getRoles().add(role);
        //配置角色到用户的关系，可以对中间表的数据进行维护
        role.getUsers().add(user);
        userDao.save(user);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testCascadeRemove() {
        //1.查询1号用户
        User user = userDao.findOne(1L);
        //2.删除1号用户
        userDao.delete(user);
    }
}
