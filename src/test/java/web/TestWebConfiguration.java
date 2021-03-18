package web;

import model.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManager;

@Configuration
@ComponentScan(basePackages = {"dao", "model", "web", "dto"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes =
                {AppJPAConfiguration.class, BlindMeetsApplication.class, TestConfiguration.class})})
@EnableWebMvc
public class TestWebConfiguration {
    @MockBean
    public EntityManager manager;
}
