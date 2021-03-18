package dao;

import model.TestConfiguration;
import model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class UserDAOTest {
    @Autowired
    private UserDAO userDAO;

    @Before
    public void configure() {
        User user1 = new User("tom123", "123");
        user1.setName("Tom");
        user1.setCity("London");
        user1.setDateOfBirth("1992-11-11");
        user1.setAge(user1.getDateOfBirth());
        User user2 = new User("kate", "321");
        user2.setName("Kate");
        user2.setCity("Moscow");
        user2.setDateOfBirth("1993-02-02");
        user2.setAge(user2.getDateOfBirth());

        userDAO.save(user1);
        userDAO.save(user2);
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

    @Test
    public void findUserByLoginPassword() {
        User user = userDAO.findByLoginAndPassword("kate", "321");
        assertNotNull(user);
        assertEquals("kate", user.getLogin());

        assertNull(userDAO.findByLoginAndPassword("noLogin", "noPassword"));
    }

    @Test
    public void findUserByLogin() {
        User user = userDAO.findByLogin("tom123");
        assertNotNull(user);
        assertEquals("tom123", user.getLogin());

        assertNull(userDAO.findByLogin("noLogin"));
    }

    @Test
    public void saveUser() {
        User saved = userDAO.saveUser(new User("jul", "456"));

        assertTrue(userDAO.findById(saved.getId()).isPresent());
    }
}