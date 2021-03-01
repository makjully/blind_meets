package dao;

import model.Interest;
import model.InterestGeneral;
import model.TestConfiguration;
import model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class InterestDAOTest {
    @Autowired
    private EntityManager manager;

    @Autowired
    private InterestDAO interestDAO;

    @Before
    public void configure() {
        User user1 = new User("tom123", "123");
        User user2 = new User("kate", "321");
        Interest interest1 = new Interest(InterestGeneral.IT, user1);
        Interest interest2 = new Interest(InterestGeneral.PHOTOGRAPHY, user1);
        Interest interest3 = new Interest(InterestGeneral.TRAVELING, user2);
        Interest interest4 = new Interest(InterestGeneral.IT, user2);

        manager.getTransaction().begin();
        manager.persist(user1);
        manager.persist(user2);
        manager.persist(interest1);
        manager.persist(interest2);
        manager.persist(interest3);
        manager.persist(interest4);
        manager.getTransaction().commit();
    }

    @Test
    public void findUsersByInterest() {
        List<String> foundIT = interestDAO.findUsersByInterest(InterestGeneral.IT);
        assertEquals(2, foundIT.size());

        List<String> foundPH = interestDAO.findUsersByInterest(InterestGeneral.PHOTOGRAPHY);
        assertEquals("tom123", foundPH.get(0));
    }
}