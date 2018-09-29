package org.schhx.springbootlearn.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "someone")
@Component
@Data
public class Someone {

    private String name;

    private Integer age;

    private String bestFriend;
}
