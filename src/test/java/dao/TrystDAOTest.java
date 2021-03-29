package dao;

import model.TestConfiguration;
import model.Tryst;
import model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class TrystDAOTest {
    @Autowired
    private TrystDAO trystDAO;

    @Autowired
    private UserDAO userDAO;

    private LocalDate now = LocalDate.now();

    @Before
    public void configure() {
        User user1 = new User("tom123", "123");
        User user2 = new User("kate", "321");
        User user3 = new User("jack17", "abc654");

        Tryst t1 = new Tryst(now, user1, user2);
        Tryst t2 = new Tryst(now, user2, user3);

        userDAO.save(user1);
        userDAO.save(user2);
        userDAO.save(user3);
        trystDAO.save(t1);
        trystDAO.save(t2);
    }

    @Test
    public void trystCount() {
        long count1 = trystDAO.findTrystsCount("kate");
        long count2 = trystDAO.findTrystsCount("tom123");
        long count3 = trystDAO.findTrystsCount("noname");

        assertEquals(2, count1);
        assertEquals(1, count2);
        assertEquals(0, count3);
    }

    @Test
    public void findByUser() {
        List<Tryst> t1 = trystDAO.findAllByUser("kate");
        List<Tryst> t2 = trystDAO.findAllByUser("jack17");
        List<Tryst> t3 = trystDAO.findAllByUser("tom123");

        assertEquals(2, t1.size());
        assertEquals(1, t2.size());
        assertEquals(1, t3.size());
    }
}