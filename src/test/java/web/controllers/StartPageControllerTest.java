package web.controllers;

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
import web.UserSession;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestWebConfiguration.class)
@AutoConfigureMockMvc
public class StartPageControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    UserDAO userDAO;

    @Test
    public void openProfile() throws Exception {
        User user = new User("test", "123");
        UserSession userSession = new UserSession();
        userSession.setUserLogin("test");

        Mockito.when(userDAO.findUserByLogin(user.getLogin())).thenReturn(user);

        mvc.perform(get("/profile").sessionAttr("user-session", userSession))
                .andExpect(status().isOk())
                .andExpect(model().attribute("user", user));
    }
}