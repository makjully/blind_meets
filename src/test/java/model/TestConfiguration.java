package model;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import web.AppJPAConfiguration;
import web.BlindMeetsApplication;
import web.TestWebConfiguration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan(basePackages = {"dao", "model", "web", "dto"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes =
                {AppJPAConfiguration.class, BlindMeetsApplication.class, TestWebConfiguration.class})})
@EnableJpaRepositories(basePackages = {"dao", "model", "web", "dto"})
@EnableTransactionManagement
@EnableAutoConfiguration
public class TestConfiguration {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("TestPersistenceUnit");
    }
}
