package org.schhx.springbootlearn.module;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class User implements Serializable{

    private String name;

    private Integer age;
}
