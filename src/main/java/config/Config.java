package config;


import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Properties;

@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"models", "example", "config"})
@Configuration
public class Config {

    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {

        var populator = new ResourceDatabasePopulator(
                new ClassPathResource("schema.sql"));
        DatabasePopulatorUtils.execute(populator, dataSource);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        factory.setDataSource(dataSource);
        factory.setPackagesToScan("models");
        factory.setJpaProperties(jpaProperties());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "validate");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.show_sql", false);
        properties.put("hibernate.format_sql", true);
        return properties;
    }
}