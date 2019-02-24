package com.demo.redis;

import com.demo.redis.model.User;
import com.demo.redis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {
    @Resource
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserService userService;

    /**
     * 测试基本连接redis读取字符串数据
     */
    @Test
    public void test01() {
//        增加
        redisTemplate.opsForValue().set("name", "张晓祥2");
        redisTemplate.opsForValue().set("age", "18");
//        查询
        String name = (String) redisTemplate.opsForValue().get("name");
        String age = (String) redisTemplate.opsForValue().get("age");
        System.out.println("查询到缓存数据: " + name + "  " + age);
//        删除
        Boolean flag = redisTemplate.delete("age");
        if (flag) {
            System.out.println("删除age成功");
        } else {
            System.out.println("删除age失败!");
        }
//        修改
        redisTemplate.opsForValue().set("name", "覆盖就是修改,哈哈22");
        System.out.println("修改后的name是: " + redisTemplate.opsForValue().get("name"));


    }

    /**
     * 测试缓存是否生效
     */
    @Test
    public void test02() {
        User user = userService.findById("1");
        System.out.println(user);

    }


}
