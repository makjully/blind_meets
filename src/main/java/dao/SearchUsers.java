package dao;

import lombok.Getter;
import lombok.Setter;
import model.Gender;
import model.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@Getter
@Setter
public class SearchUsers {
    @Pattern(regexp = "\\p{Digit}+", message = "Age should contain only digits")
    @Positive
    private int age;

    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Name should contain only letters")
    private String city;

    private Gender gender;

    private String[] userInterests;

    private final EntityManager manager;

    public SearchUsers(EntityManager manager) {
        this.manager = manager;
    }

    public List<User> findUsersByCriteria() {
        List<Predicate> predicates = new ArrayList<>();

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery cq = builder.createQuery();
        Root<User> user = cq.from(User.class);

        if (age != 0) {
            predicates.add(
                    builder.equal(user.get("age"), age)
            );
        }

        if (city != null) {
            predicates.add(
                    builder.like(user.get("city"), city)
            );
        }

        if (gender != null) {
            predicates.add(
                    builder.equal(user.get("gender"), gender)
            );
        }

        if (userInterests.length > 0) {
            builder.or((Predicate[]) Arrays.stream(userInterests).map(i -> builder.isMember(i, user.get("interests"))).toArray());

            for (String interest : userInterests) {
                predicates.add(
                        builder.isMember(interest, user.get("interests"))
                );
            }
        }

        cq.select(user)
                .where(predicates.toArray(new Predicate[]{}));

        List<User> found = manager.createQuery(cq).getResultList();

        return returnRandomVariants(found);
    }

    private List<User> returnRandomVariants(List<User> found) {
        Collections.shuffle(found);

        if (found.size() > 4) {
            found = new ArrayList<>(found.subList(0, 3));
        }
        return found;
    }
}
