package com.redbean.test.redis;

import com.redbean.test.redis.bean.Movie;
import com.redbean.test.redis.bean.Student;
import com.redbean.test.redis.config.RedisUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {


    private String userKey_prefix = "user:";
    private String userinfoKey_suffix = ":info";
    private int userKey_timeOut = 60 * 60 * 24 * 7;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    private void contextLoads() {
        Jedis jedis = new Jedis("192.168.91.222", 6379);
        System.out.println("连接成功" + jedis.ping());

        Set<String> keys = jedis.keys("*");
        for (Iterator iterator = keys.iterator(); iterator.hasNext(); ) {
            String key = (String) iterator.next();
            System.out.println(key);
        }
        System.out.println("jedis.exists====>" + jedis.exists("k2"));
        System.out.println(jedis.ttl("k1"));


        System.out.println(jedis.get("k1"));
        jedis.set("k4", "k4_Redis");
        System.out.println("----------------------------------------");
        jedis.mset("str1", "v1", "str2", "v2", "str3", "v3");
        System.out.println(jedis.mget("str1", "str2", "str3"));

    }


    @Test
    private void testRedis() {


        Movie movie = new Movie();
        movie.setId("111");
        movie.setName("红海行动");

        String redisKey = userKey_prefix + movie.getId() + userinfoKey_suffix;
        Jedis jedis = redisUtil.getJedis();
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);
        System.out.println("-------------------------");
//        String setex = jedis.setex(r      0`````````````edisKey, userKey_timeOut, JSON.toJSONString(movie));
        Long ttl = jedis.ttl("user:111:info"); //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println(ttl);
//        System.out.println(setex);
        jedis.close();

    }


    @Test
    private void testBeanXML() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-student.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student);

    }

    @Test
    private void testProperties() {
        Properties properties = new Properties();
        properties.put("testA", "aaa");
        properties.put("testB", "bbb");
        properties.put("testC", "ccc");
        properties.put("testD", "ddd");

        System.out.println(properties);
    }

}
