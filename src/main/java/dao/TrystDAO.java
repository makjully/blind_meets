package dao;

import model.Tryst;
import model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TrystDAO extends JpaRepository<Tryst, Integer> {

    @Query("select count(t.id) from Tryst t where t.inviter.login = :user OR t.invitee.login = :user")
    public long findTrystsCount(@Param("user") String user);

    @Query("from Tryst t where t.inviter.login = :user OR t.invitee.login = :user")
    public List<Tryst> findAllByUser(@Param("user") String user);

    @Transactional
    public default Tryst saveTryst(Tryst tryst) {
        return save(tryst);
    }
}
