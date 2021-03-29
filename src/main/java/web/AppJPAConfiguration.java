package web;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"dao", "model", "web", "dto"})
@EnableJpaRepositories(basePackages = {"dao"})
@EnableTransactionManagement
@EntityScan("model")
public class AppJPAConfiguration {
}
