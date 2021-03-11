package web.controllers;

import dao.InterestDAO;
import dao.UserDAO;
import model.Gender;
import model.Interest;
import model.InterestGeneral;
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

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestWebConfiguration.class)
@AutoConfigureMockMvc
public class RegistrationControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    UserDAO userDAO;

    @MockBean
    InterestDAO interestDAO;

    @MockBean
    private EntityTransaction transaction;

    @Autowired
    private EntityManager manager;

    @Test
    public void register() throws Exception {
        User user = new User();
        user.setLogin("test");
        user.setPassword("123");
        user.setName("John");
        user.setGender(Gender.MALE);
        user.setDateOfBirth("11/11/1995");
        user.setCity("Amsterdam");
        Interest interest1 = new Interest(InterestGeneral.IT, user);
        Interest interest2 = new Interest(InterestGeneral.PHOTOGRAPHY, user);
        Interest interest3 = new Interest(InterestGeneral.MUSIC, user);
        user.setInterests(Arrays.asList(interest1, interest2, interest3));

        Mockito.when(manager.getTransaction()).thenReturn(transaction);
        Mockito.when(userDAO.saveUser(user)).thenReturn(user);

        mvc.perform(post("/register")
                .flashAttr("newUser", user)
                .param("userInterests", "IT", "PHOTOGRAPHY", "MUSIC")
        )
                .andExpect(status().is3xxRedirection());

        Mockito.when(interestDAO.addInterest(user.getInterests().get(0))).thenReturn(interest1);
        Mockito.when(interestDAO.addInterest(user.getInterests().get(1))).thenReturn(interest2);
        Mockito.when(interestDAO.addInterest(user.getInterests().get(2))).thenReturn(interest3);

        Mockito.verify(userDAO, Mockito.atLeast(1))
                .saveUser(user);
    }
}