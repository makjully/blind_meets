package dao;

import model.Interest;
import model.InterestGeneral;
import model.TestConfiguration;
import model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class InterestDAOTest {

    @Autowired
    private InterestDAO interestDAO;

    @Autowired
    private UserDAO userDAO;

    @Before
    public void configure() {
        User user1 = new User("tom123", "123");
        User user2 = new User("kate", "321");
        Interest interest1 = new Interest(InterestGeneral.IT, user1);
        Interest interest2 = new Interest(InterestGeneral.PHOTOGRAPHY, user1);
        Interest interest3 = new Interest(InterestGeneral.TRAVELING, user2);
        Interest interest4 = new Interest(InterestGeneral.IT, user2);

        userDAO.save(user1);
        userDAO.save(user2);
        interestDAO.save(interest1);
        interestDAO.save(interest2);
        interestDAO.save(interest3);
        interestDAO.save(interest4);
    }

    @Test
    public void findUsersByInterest() {
        List<String> foundIT = interestDAO.findUsersByInterest(InterestGeneral.IT);
        assertEquals(2, foundIT.size());

        List<String> foundPH = interestDAO.findUsersByInterest(InterestGeneral.PHOTOGRAPHY);
        assertEquals("tom123", foundPH.get(0));
    }
}