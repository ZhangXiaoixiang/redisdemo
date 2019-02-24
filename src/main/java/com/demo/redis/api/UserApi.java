package com.demo.redis.api;


import com.demo.redis.model.User;
import com.demo.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * UserApi
 *
 * @author 10905 2019/2/24
 * @version 1.0
 */
@RestController
public class UserApi {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public String findAll() {
        System.out.println("浏览器客户端调用了findAll");
        List<User> all = userService.findAll();

        return all.toString();
    }
}
