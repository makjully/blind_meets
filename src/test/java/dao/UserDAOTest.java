package dao;

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
public class UserDAOTest {
    @Autowired
    private EntityManager manager;

    @Autowired
    private UserDAO userDAO;

    @Before
    public void configure() {
        User user1 = new User("tom123", "123");
        user1.setName("Tom");
        user1.setCity("London");
        user1.setDateOfBirth("11/11/1992");
        user1.setAge(user1.getDateOfBirth());
        User user2 = new User("kate", "321");
        user2.setName("Kate");
        user2.setCity("Moscow");
        user2.setDateOfBirth("02/02/1993");
        user2.setAge(user2.getDateOfBirth());

        manager.getTransaction().begin();
        manager.persist(user1);
        manager.persist(user2);
        manager.getTransaction().commit();
    }

    @Test
    public void findByName() {
        List<User> found = userDAO.findByName("Tom");
        assertEquals("tom123", found.get(0).getLogin());
    }

    @Test
    public void findByCity() {
        List<User> found = userDAO.findByCity("Moscow");
        assertEquals("Kate", found.get(0).getName());
    }

    @Test
    public void findByAge() {
        List<User> found = userDAO.findByAge(28);
        assertEquals(2, found.size());
    }
}