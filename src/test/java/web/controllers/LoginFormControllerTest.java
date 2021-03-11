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

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestWebConfiguration.class)
@AutoConfigureMockMvc
public class LoginFormControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    UserDAO dao;

    @MockBean
    private EntityTransaction transaction;

    @Autowired
    private EntityManager manager;

    @Test
    public void correctUser() throws Exception {
        UserSession userSession = new UserSession();
        User user = new User("test", "123");
        Mockito.when(manager.getTransaction()).thenReturn(transaction);
        Mockito.when(dao.findUserByLoginPassword("test", "123")).thenReturn(user);

        mvc.perform(post("/login")
                .param("login", "test")
                .param("password", "123")
                .sessionAttr("user-session", userSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attribute("user", user));
    }
}