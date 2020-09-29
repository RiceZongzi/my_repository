package com.rice;

import com.rice.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wgz
 * @description
 * @date 2020/9/29 10:56
 */
public class JDBCTest01 {

    public static void main(String[] args) throws Exception {
        // 注册驱动
         Class.forName("com.mysql.jdbc.Driver");
        // 获取数据库连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql:///mybatis", "root", "root");
        // 定义sql语句
        String sql = "select * from user ";
        // 获取执行sql的对象 Statement
        Statement statement = connection.createStatement();
        // 执行sql
        ResultSet resultSet = statement.executeQuery(sql);
        // 处理结果
        List<User> userList = new ArrayList<>();
        while(resultSet.next()){
            //获取数据
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setUsername(resultSet.getString(2));
            user.setBirthday(resultSet.getDate(3));
            user.setSex(resultSet.getString(4));
            user.setAddress(resultSet.getString(5));
            userList.add(user);
        }
        userList.forEach(System.out::println);
        // 释放资源
        statement.close();
        connection.close();
    }
}
