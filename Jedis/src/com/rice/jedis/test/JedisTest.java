package com.rice.jedis.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author ：by wgz
 * @description：
 * @Date ：Created in 2020/9/27 14:52
 * @Version: $
 */
public class JedisTest {

    private Jedis jedis;

    @Before
    public void init()throws Exception{
        // 1. 获取连接
        jedis = new Jedis("localhost",6379);
    }

    @After
    public void destroy()throws Exception{
        // 3. 关闭连接
        jedis.close();
    }

    @Test
    public void testSetString(){
        // 2. 操作
        jedis.set("address","Peking");

        // setex()方法，存储可以指定过期时间的 key value
        // Token=12345678 键值对存入redis，并且20秒后自动删除该键值对
        jedis.setex("Token",20,"12345678");
    }

    @Test
    public void testGetString(){
        // 2. 操作
        System.out.println(jedis.get("address"));
    }

    @Test
    public void testHSetHash(){
        // 2. 操作
        jedis.hset("user", "name", "Bill");
        jedis.hset("user", "age", "24");
        jedis.hset("user", "gender", "male");
    }

    @Test
    public void testHGetHash(){
        // 2. 操作
        // 获取单个属性
        System.out.println(jedis.hget("user", "name"));
        // 获取整个Hash
        Map<String, String> user = jedis.hgetAll("user");
        // 打印
        System.out.println(user);
        // 单独打印
        Set<String> keySet = user.keySet();
        keySet.forEach(s -> System.out.println(s + "=" + user.get(s)));
    }

    @Test
    public void testPushList(){
        // 2. 操作
        // 从左边存
        jedis.lpush("mylist", "a","b","c");
        // 从右边存
        jedis.rpush("mylist", "a","b","c");
        // 实际的保存顺序应该为cbaabc
    }

    @Test
    public void testRangeList(){
        // 2. 操作
        // 范围获取，
        List<String> mylist = jedis.lrange("mylist", 0, -1);
        // 遍历打印
        System.out.println(mylist);
    }

    @Test
    public void testPopList(){
        // 2. 操作
        // 从左侧弹出
        System.out.println(jedis.lpop("mylist"));
        // 从右侧弹出
        System.out.println(jedis.rpop("mylist"));
        // 再次遍历，应该为baab
        this.testRangeList();
    }
}
