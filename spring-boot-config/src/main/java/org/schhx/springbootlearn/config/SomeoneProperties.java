package org.schhx.springbootlearn.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "someone")
@Component
@Data
public class SomeoneProperties {

    private String name;

    private Integer age;

    private String bestFriend;
}
