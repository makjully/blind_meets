package model;

import converters.UserInterestConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Interest {
    @Id
    @GeneratedValue
    private int id;

    @Convert(converter = UserInterestConverter.class)
    private InterestGeneral interest;

    @ManyToOne
    private User user;

    public Interest(InterestGeneral interest, User user) {
        this.interest = interest;
        this.user = user;
    }
}
