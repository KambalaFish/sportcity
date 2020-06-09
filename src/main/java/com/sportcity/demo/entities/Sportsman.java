package com.sportcity.demo.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="sportsman")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

public class Sportsman extends AbstractEntity<Long> {

    @Column(name="name", nullable = false)
    private String name;
    @Column(name="club_name", nullable = false)
    private String club_name;

    /*https://www.baeldung.com/jpa-many-to-many*/
    @ManyToMany
    @JoinTable(
            name = "mentoring",
            joinColumns = @JoinColumn(name = "sportsman_id"),
            inverseJoinColumns = @JoinColumn(name = "coach_id")
    )
    private List<Coach> coaches = new ArrayList<>();

    /*
    public void addCoach(Coach coach){
        coaches.add(coach);
        coach.getSportsmen().add(this);
    }
    public void removeCoach(Coach coach){
        coaches.remove(coach);
        coach.getSportsmen().remove(this);
    }
    */

    @OneToMany(mappedBy = "sportsman", fetch = FetchType.LAZY)
    private List<Ability> abilities = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "participation",
            joinColumns = @JoinColumn(name = "sportsman_id"),
            inverseJoinColumns = @JoinColumn(name = "competition_id")
    )
    private List<Competition> competitions = new ArrayList<>();
}
