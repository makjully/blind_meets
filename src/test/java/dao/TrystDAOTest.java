package dao;

import model.TestConfiguration;
import model.Tryst;
import model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TrystDAOTest {
    @Autowired
    private EntityManager manager;
    @Autowired
    private TrystDAO trystDAO;
    private Date now = new Date();

    @Before
    public void configure() {
        User user1 = new User("tom123", "123");
        User user2 = new User("kate", "321");
        User user3 = new User("jack17", "abc654");

        Tryst t1 = new Tryst(now, user1, user2);
        Tryst t2 = new Tryst(now, user2, user3);
        t1.setFinished(true);

        manager.getTransaction().begin();
        manager.persist(user1);
        manager.persist(user2);
        manager.persist(user3);
        manager.persist(t1);
        manager.persist(t2);
        manager.getTransaction().commit();
    }

    @Test
    public void trystCount() {
        long count1 = trystDAO.trystCount("kate");
        long count2 = trystDAO.trystCount("tom123");
        long count3 = trystDAO.trystCount("noname");

        assertEquals(2, count1);
        assertEquals(1, count2);
        assertEquals(0, count3);
    }

    @Test
    public void findArchive() {
        List<Tryst> t1 = trystDAO.findArchive("kate");
        List<Tryst> t2 = trystDAO.findArchive("jack17");
        List<Tryst> t3 = trystDAO.findArchive("tom123");

        assertEquals(1, t1.size());
        assertEquals(0, t2.size());
        assertEquals(1, t3.size());
    }
}