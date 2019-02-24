package com.demo.redis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * User
 *lombok简化实体类User(写构造方法等简写)
 *
 * @Entity 声明实体类,供jpa使用
 * @Table(name="user") 声明映射数据库的表(可省略,但是个人不建议)
 * @Id 表示指定表主键
 *
 * @author 10905 2018/12/9
 * @version 1.0
 */
@Data//getset方法
@ToString//toString方法
@NoArgsConstructor//无参
@AllArgsConstructor//全参
@Entity
@Table(name="user")
public class User implements Serializable {
    /**
     *  @Id表示指定表主键
     */
    @Id
    private String id;
    private String name;
    private String password;

}
