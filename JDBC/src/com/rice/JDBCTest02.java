package com.rice;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author wgz
 * @description
 * @date 2020/9/29 10:56
 */
public class JDBCTest02 {

    public static void main(String[] args) throws Exception {

        // 加载配置文件
        Properties properties = new Properties();
        InputStream resourceAsStream = JDBCTest02.class.getClassLoader().getResourceAsStream("druid.properties");
        properties.load(resourceAsStream);
        // 获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        // 获取连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}
