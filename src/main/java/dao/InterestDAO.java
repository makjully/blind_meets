package dao;

import javax.persistence.EntityManager;
import java.util.List;

public class InterestDAO {
    private final EntityManager manager;

    public InterestDAO(EntityManager manager) {
        this.manager = manager;
    }

    public List<String> findUsersByInterest(String interest) {
        return manager.createQuery("select i.user.login from Interest i where i.interest like : interest", String.class)
                .setParameter("interest", interest)
                .getResultList();
    }
}