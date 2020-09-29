package com.rice.darasource.jdbctemplate;

import com.rice.darasource.util.DruidJDBCUtil;
import com.rice.entity.User;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author wgz
 * @description
 * @date 2020/9/29 15:50
 */
public class JDBCTemplateTest {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidJDBCUtil.getDataSource());

    @Test
    public void test1() {
        String sql = "select * from user where id = ?";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, 1);
        // {id=1, username=王五, birthday=null, sex=2, address=null, name=null, password=null}
        System.out.println(map);
    }

    @Test
    public void test2() {
        String sql = "select * from user";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        mapList.forEach(System.out::println);
        mapList.forEach(stringObjectMap -> System.out.println(stringObjectMap));
    }

    @Test
    public void test3() {
        String sql = "select * from user";
        jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setBirthday(resultSet.getDate(3));
                user.setSex(resultSet.getString(4));
                user.setAddress(resultSet.getString(5));
                return user;
            }
        }).forEach(user -> System.out.println(user));
    }

    @Test
    public void test4() {
        String sql = "select * from user";
        jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class))
                .forEach(user -> System.out.println(user));
    }

    @Test
    public void test5() {
        String sql = "select count(1) from user";
        System.out.println(jdbcTemplate.queryForObject(sql, Long.class));
    }
}
