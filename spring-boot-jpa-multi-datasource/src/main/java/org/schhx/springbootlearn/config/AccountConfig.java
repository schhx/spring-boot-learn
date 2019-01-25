package org.schhx.springbootlearn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.*;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "accountEntityManagerFactory",
        transactionManagerRef = "accountTransactionManager",
        basePackages = {"org.schhx.springbootlearn.dao.account"})
public class AccountConfig {

    @Autowired
    @Qualifier("accountDataSource")
    private DataSource accountDataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private HibernateProperties hibernateProperties;

    @Bean(name = "accountEntityManager")
    public EntityManager accountEntityManager(EntityManagerFactoryBuilder builder) {
        return accountEntityManagerFactory(builder).getObject().createEntityManager();
    }

    @Bean(name = "accountEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean accountEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(accountDataSource)
                .properties(getVendorProperties())
                .packages("org.schhx.springbootlearn.entity.account")
                .persistenceUnit("accountPersistenceUnit")
                .build();
    }

    @Bean(name = "accountTransactionManager")
    public PlatformTransactionManager accountTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(accountEntityManagerFactory(builder).getObject());
    }

    private Map<String, Object> getVendorProperties() {
        return new LinkedHashMap<>(this.hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(),
                new HibernateSettings())
        );
    }

}
