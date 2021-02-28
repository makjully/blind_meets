package model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestEntityManager {
    @Autowired
    private EntityManager manager;

    @Test
    public void smokeTest() {
        User user1 = new User("tom123", "123");
        User user2 = new User("kate123", "123");

        Interest interest1 = new Interest(InterestGeneral.PHOTOGRAPHY, user1);
        Interest interest2 = new Interest(InterestGeneral.MUSIC, user1);
        Interest interest3 = new Interest(InterestGeneral.IT, user2);
        Interest interest4 = new Interest(InterestGeneral.CAT_LOVER, user2);

        Tryst tryst = new Tryst(new Date(), user1, user2);

        Message message1 = new Message("Hello", user1, user2);
        Message message2 = new Message("Hi!", user2, user1);

        manager.getTransaction().begin();
        manager.persist(user1);
        manager.persist(user2);
        manager.persist(interest1);
        manager.persist(interest2);
        manager.persist(interest3);
        manager.persist(interest4);
        manager.persist(tryst);
        manager.persist(message1);
        manager.persist(message2);
        manager.getTransaction().commit();
    }
}
