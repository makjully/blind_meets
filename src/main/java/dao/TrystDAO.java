package dao;

import model.Tryst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class TrystDAO {
    private EntityManager manager;

    @Autowired
    public TrystDAO(EntityManager manager) {
        this.manager = manager;
    }

    public long trystCount(String user) {
        return manager.createQuery("select count(t.id) from Tryst t where t.inviter.login = :user OR t.invitee.login = :user",
                Long.class)
                .setParameter("user", user)
                .setParameter("user", user)
                .getSingleResult();
    }

    public List<Tryst> findArchive(String user) {
        return manager.createQuery("from Tryst t where (t.inviter.login = :user OR t.invitee.login = :user) AND t.isFinished = true",
                Tryst.class)
                .setParameter("user", user)
                .setParameter("user", user)
                .getResultList();
    }
}
