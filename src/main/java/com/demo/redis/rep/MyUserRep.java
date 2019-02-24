package com.demo.redis.rep;

import com.demo.redis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRep extends JpaRepository<User, String> {
    User getById(String id);


}
