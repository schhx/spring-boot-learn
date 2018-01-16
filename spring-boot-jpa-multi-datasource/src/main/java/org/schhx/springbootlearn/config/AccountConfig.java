package org.schhx.springbootlearn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
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
import java.util.Map;

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

    @Bean(name = "accountEntityManager")
    public EntityManager accountEntityManager(EntityManagerFactoryBuilder builder) {
        return accountEntityManagerFactory(builder).getObject().createEntityManager();
    }

    @Bean(name = "accountEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean accountEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(accountDataSource)
                .properties(getVendorProperties(accountDataSource))
                .packages("org.schhx.springbootlearn.entity.account")
                .persistenceUnit("accountPersistenceUnit")
                .build();
    }

    @Bean(name = "accountTransactionManager")
    public PlatformTransactionManager accountTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(accountEntityManagerFactory(builder).getObject());
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

}
