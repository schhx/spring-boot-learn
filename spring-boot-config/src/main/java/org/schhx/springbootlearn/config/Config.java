package org.schhx.springbootlearn.config;

import org.schhx.springbootlearn.bean.Someone3;
import org.schhx.springbootlearn.bean.Someone2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shanchao
 * @date 2018-09-12
 */
@Configuration
@EnableConfigurationProperties({Someone2.class})
public class Config {

    @Bean
    @ConfigurationProperties(prefix = "someone")
    public Someone3 someone3() {
        return new Someone3();
    }
}
