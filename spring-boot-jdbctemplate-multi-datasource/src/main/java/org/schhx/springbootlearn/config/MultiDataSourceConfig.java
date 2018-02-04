package org.schhx.springbootlearn.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class MultiDataSourceConfig {

    @Bean("userDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.user")
    @Primary
    public DataSource userDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "userJdbcTemplate")
    @Primary
    public JdbcTemplate userJdbcTemplate(
            @Qualifier("userDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "userTransactionManager")
    @Primary
    public DataSourceTransactionManager userTransactionManager(@Qualifier("userDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("accountDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.account")
    public DataSource accountDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "accountJdbcTemplate")
    public JdbcTemplate accountJdbcTemplate(
            @Qualifier("accountDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "accountTransactionManager")
    public DataSourceTransactionManager accountTransactionManager(@Qualifier("accountDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}
