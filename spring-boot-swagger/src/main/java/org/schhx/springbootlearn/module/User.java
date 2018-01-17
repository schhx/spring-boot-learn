package org.schhx.springbootlearn.module;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class User {

    private Long id;

    @ApiModelProperty(required = true, value = "年龄")
    private Long age;

    @ApiModelProperty(required = true, value = "名称")
    private String name;

}
