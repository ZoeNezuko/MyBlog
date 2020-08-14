package com.jie.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

/**
 * @author Jie Pu
 */
@Data
//@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
/**
 * 因为是做前后端分离，而前后端数据交互用的是 json 格式。
 * 那么 User 对象就会被转换为 json 数据。 而本项目使用 jpa 来做实体类的持久化，
 * jpa 默认会使用 hibernate, 在 jpa 工作过程中，就会创造代理类来继承 User ，
 * 并添加 handler 和 hibernateLazyInitializer 这两个无须 json 化的属性，
 * 所以这里需要用 JsonIgnoreProperties 把这两个属性忽略掉。
 */
public class User {

    /**
     * PK is ID
     */
    @Id // Declare the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primary key auto increment
    @Column(name = "id") // The corresponding name in the table
    private Integer id;

    // constructor
    public User() {
        enable = true;
    }

    /**
     * Name
     */
    private String username;

    /**
     * Password
     */
    private String password;

    /**
     * Email
     */
    private String email;

    /**
     * User status.
     */
    private boolean enable;

    // Store role information
    @Transient
    private List<Role> roles;

}
