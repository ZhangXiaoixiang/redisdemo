package com.demo.redis.service.impl;

import com.demo.redis.model.User;
import com.demo.redis.rep.MyUserRep;
import com.demo.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Optional;

/**
 * UserServiceImpl
 *
 * @author 10905 2019/2/24
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private MyUserRep myUserRep;
    @Autowired
    private RedisTemplate redisTemplate;
    //    为redis存入数据定义一个变量key值
    private static final String ALL_USER = "ALL_USER_LIST";

    @Override
    public List<User> findAll() {

        return myUserRep.findAll();
    }

    @Override
    public User findById(String id) {
        /**
         * 设置序列化方式--如果没有配置序列化策略就加上下面2句
         */
//        RedisSerializer redisSerializer=new StringRedisSerializer();
//        redisTemplate.setKeySerializer(redisSerializer);

//        在缓存查询
        List<User> userList = redisTemplate.opsForList().range(ALL_USER, 0, -1);
        if (userList != null && userList.size() > 0) {
            System.out.println("缓存有数据-------------"+userList.size());
            System.out.println(userList);
            for (User user :  userList) {
                System.out.println(user.getId());
                if (user.getId().equals(id)) {
                    System.out.println("查询到了--------"+user);
                    return user;
                }

            }
//            return userList.get(0);
        }
//            数据库查询
        User user = myUserRep.getById(id);
        System.out.println(user.toString());
        if ( user!= null) {
//                如果在数据库查到了,先存入缓存再返回给调用者
                System.out.println("在数据库查询的,先存入redis缓存再返回给调用者");
//    user.toString()存为string类型了,所以在缓存查询到也是String而不是user序列化的对象
                redisTemplate.opsForList().leftPushAll(ALL_USER, user);
//                redisTemplate.opsForValue().set(ALL_USER, user);
                return user;
            } else {
                System.out.println("数据库没有查询到");

            }

        return user;




    }
}
