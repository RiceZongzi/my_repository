package com.rice.dao;

import com.rice.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user ")
    List<User> findAll();

    /**
     * 保存用户
     * @param user
     */
    @Insert("insert into user(username, address, sex, birthday) " +
            "values (#{username},#{address},#{sex},#{birthday})")
    void saveUser(User user);
}
