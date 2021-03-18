package dao;

import model.Interest;
import model.InterestGeneral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface InterestDAO extends JpaRepository<Interest, Integer> {

    @Query("select i.user.login from Interest i where i.interest like :interest")
    public List<String> findUsersByInterest(@Param("interest") InterestGeneral interest);

    @Transactional
    public default Interest addInterest(Interest interest) {
        return save(interest);
    }
}