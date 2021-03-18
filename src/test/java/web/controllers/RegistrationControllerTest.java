package web.controllers;

import dao.InterestDAO;
import dao.UserDAO;
import dto.ConvertingService;
import dto.UserDTO;
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

    @Test
    public void registerSuccessful() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin("test");
        userDTO.setPassword("88888888");
        userDTO.setName("Kate");
        userDTO.setDateOfBirth("1995-02-21");
        userDTO.setCity("Paris");
        userDTO.setGender(Gender.FEMALE);
        userDTO.setUserInterests(new String[]{"IT", "WINE", "CAT_LOVER"});

        User user = ConvertingService.transferFromDTOtoUser(userDTO);

        Mockito.when(userDAO.saveUser(user)).thenReturn(user);
        Mockito.when(interestDAO.addInterest(user.getInterests().get(0))).thenReturn(new Interest(InterestGeneral.IT, user));
        Mockito.when(interestDAO.addInterest(user.getInterests().get(1))).thenReturn(new Interest(InterestGeneral.WINE, user));
        Mockito.when(interestDAO.addInterest(user.getInterests().get(2))).thenReturn(new Interest(InterestGeneral.CAT_LOVER, user));

        mvc.perform(post("/register")
                .flashAttr("newUser", userDTO)
        )
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void registerUnsuccessful() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin("test");
        userDTO.setPassword("777");
        userDTO.setName("Smth333");
        userDTO.setDateOfBirth("1995-02-21");
        userDTO.setCity("Paris1");
        userDTO.setGender(Gender.FEMALE);
        userDTO.setUserInterests(new String[]{"IT", "WINE", "CAT_LOVER"});

        User user = ConvertingService.transferFromDTOtoUser(userDTO);

        Mockito.when(userDAO.saveUser(user)).thenReturn(user);
        Mockito.when(interestDAO.addInterest(user.getInterests().get(0))).thenReturn(new Interest(InterestGeneral.IT, user));
        Mockito.when(interestDAO.addInterest(user.getInterests().get(1))).thenReturn(new Interest(InterestGeneral.WINE, user));
        Mockito.when(interestDAO.addInterest(user.getInterests().get(2))).thenReturn(new Interest(InterestGeneral.CAT_LOVER, user));

        mvc.perform(post("/register")
                .flashAttr("newUser", userDTO)
        )
                .andExpect(status().isOk());
    }
}