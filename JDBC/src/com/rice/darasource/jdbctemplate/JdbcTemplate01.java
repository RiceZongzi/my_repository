package com.rice.darasource.jdbctemplate;

import com.rice.darasource.util.DruidJDBCUtil;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author wgz
 * @description
 * @date 2020/9/29 15:40
 */
public class JdbcTemplate01 {

    public static void main(String[] args) {
        // Jar
        // 创建 JDBCTemplate 对象
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidJDBCUtil.getDataSource());
        // sql
        String sql = "update user set sex = '1' where id = ?";
        int update = jdbcTemplate.update(sql, 10);
        System.out.println(update);
    }
}
