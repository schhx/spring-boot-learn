package org.schhx.springbootlearn.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {

    private String id;

    private String username;

    private Integer age;
}
