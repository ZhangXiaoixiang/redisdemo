package com.demo.redis.listener;

import com.demo.redis.model.User;
import com.demo.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * UserListener
 * 监听器
 *
 * @author 10905 2019/2/24
 * @version 1.0
 */
@WebListener
@Configuration
public class UserListener implements ServletContextListener {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserService userService;
    //    为redis存入数据定义一个变量key值
    private static final String ALL_USER = "ALL_USER_LIST";

    /**
     * 初识化上下文执行的操作
     *
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("-----------进入初始化上下文监听器---------------");
//        全查询数据库
        List<User> all = userService.findAll();
//       清除缓存中的数据
        redisTemplate.delete(ALL_USER);
//        将数据库查询到的额数据放到缓存,方法leftPushAll
        redisTemplate.opsForList().leftPushAll(ALL_USER,all);
//        查询所有用户数据
        List<User> userList = redisTemplate.opsForList().range(ALL_USER, 0, -1);
        System.out.println("缓存数据中用户数为:"+userList.size()+"人");
        System.out.println("-------------初始化结束--------");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("-----------------销毁上下文-------------------------");
    }
}
