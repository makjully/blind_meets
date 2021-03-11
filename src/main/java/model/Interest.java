package model;

import converters.UserInterestConverter;

import javax.persistence.*;

@Entity
public class Interest {
    @Id
    @GeneratedValue
    private int id;

    @Convert(converter = UserInterestConverter.class)
    private InterestGeneral interest = InterestGeneral.NONE;

    @ManyToOne (cascade = CascadeType.ALL)
    private User user;

    public Interest() {}

    public Interest(InterestGeneral interest, User user) {
        this.interest = interest;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InterestGeneral getInterest() {
        return interest;
    }

    public void setInterest(InterestGeneral interest) {
        this.interest = interest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
