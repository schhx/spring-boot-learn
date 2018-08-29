package org.schhx.springbootlearn.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean("userDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.user")
    @Primary
    public DataSource userDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("accountDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.account")
    public DataSource accountDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

}
