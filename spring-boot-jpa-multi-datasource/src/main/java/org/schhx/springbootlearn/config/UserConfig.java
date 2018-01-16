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

import javax.sql.DataSource;
import javax.persistence.EntityManager;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "userEntityManagerFactory",
        transactionManagerRef = "userTransactionManager",
        basePackages = {"org.schhx.springbootlearn.dao.user"})
public class UserConfig {

    @Autowired
    @Qualifier("userDataSource")
    private DataSource userDataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Primary
    @Bean(name = "userEntityManager")
    public EntityManager userEntityManager(EntityManagerFactoryBuilder builder) {
        return userEntityManagerFactory(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "userEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean userEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(userDataSource)
                .properties(getVendorProperties(userDataSource))
                .packages("org.schhx.springbootlearn.entity.user")
                .persistenceUnit("userPersistenceUnit")
                .build();
    }

    @Primary
    @Bean(name = "userTransactionManager")
    public PlatformTransactionManager userTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(userEntityManagerFactory(builder).getObject());
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

}
