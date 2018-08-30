package org.schhx.springbootlearn.model;

import javax.persistence.*;

@Table(name = "user")
public class User {
    /**
     * UUID
     */
    @Id
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 获取UUID
     *
     * @return id - UUID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置UUID
     *
     * @param id UUID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }
}