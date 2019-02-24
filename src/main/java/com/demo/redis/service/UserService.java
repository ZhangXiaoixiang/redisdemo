package com.demo.redis.service;



import com.demo.redis.model.User;

import java.util.List;

/**
 * UserService
 *
 * @author 10905 2019/2/24
 * @version 1.0
 */
public interface UserService {
    List<User> findAll();
    User findById(String id);
}
