package org.schhx.springbootlearn.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Table(name = "user")
@Data
@Accessors(chain = true)
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
}