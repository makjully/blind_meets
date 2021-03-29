package model;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import web.*;

@Configuration
@ComponentScan(basePackages = {"dao", "model", "converters"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes =
                {AppJPAConfiguration.class, BlindMeetsApplication.class, TestWebConfiguration.class, SecurityConfiguration.class, DetailsService.class})})
@EnableJpaRepositories(basePackages = {"dao"})
@EnableTransactionManagement
@EnableAutoConfiguration
@EntityScan("model")
public class TestConfiguration {
}
