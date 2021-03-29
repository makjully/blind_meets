package web.controllers;

import dao.InterestDAO;
import dao.UserDAO;
import model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import web.TestWebConfiguration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestWebConfiguration.class)
@AutoConfigureMockMvc
public class ProfileControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    UserDAO userDAO;

    @Test
    public void openProfile() throws Exception {
        User user = new User("test", "123");

        Mockito.when(userDAO.findByLogin(user.getLogin())).thenReturn(user);

        mvc.perform(get("/user/profile").with(user("test").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("user", user));
    }
}