package web;

import model.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import javax.persistence.EntityManager;

@Configuration
@ComponentScan(basePackages = {"dao", "model", "web"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes =
                {AppJPAConfiguration.class, BlindMeetsApplication.class, TestConfiguration.class})})
public class TestWebConfiguration {
    @MockBean
    public EntityManager manager;
}
