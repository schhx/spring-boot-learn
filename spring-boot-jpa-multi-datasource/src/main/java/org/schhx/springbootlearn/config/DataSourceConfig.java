package org.schhx.springbootlearn.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean("userDataSource")
    @ConfigurationProperties(prefix = "multi-datasource.user")
    @Primary
    public DataSource userDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("accountDataSource")
    @ConfigurationProperties(prefix = "multi-datasource.account")
    public DataSource accountDataSource(){
        return DataSourceBuilder.create().build();
    }

}
