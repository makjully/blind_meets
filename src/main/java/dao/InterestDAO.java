package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class InterestDAO {
    private final EntityManager manager;

    @Autowired
    public InterestDAO(EntityManager manager) {
        this.manager = manager;
    }

    public List<String> findUsersByInterest(String interest) {
        return manager.createQuery("select i.user.login from Interest i where i.interest like :interest", String.class)
                .setParameter("interest", interest)
                .getResultList();
    }
}