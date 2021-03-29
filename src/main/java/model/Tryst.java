package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tryst {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private LocalDate date;

    @Column
    private boolean isFinished;

    @ManyToOne
    private User inviter;

    @ManyToOne
    private User invitee;

    public Tryst(LocalDate date, User inviter, User invitee) {
        this.date = date;
        this.inviter = inviter;
        this.invitee = invitee;
        isFinished = false;
    }
}
