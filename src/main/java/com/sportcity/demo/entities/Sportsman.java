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

public class Sportsman extends AbstractEntity<Integer> {

    @Column(name="name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    /*https://www.baeldung.com/jpa-many-to-many*/
    @ManyToMany
    @JoinTable(
            name = "mentoring",
            joinColumns = @JoinColumn(name = "sportsman_id"),
            inverseJoinColumns = @JoinColumn(name = "coach_id")
    )
    private List<Coach> coaches = new ArrayList<>();

    @OneToMany(mappedBy = "sportsman", fetch = FetchType.LAZY)
    private List<Ability> abilities = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "participation",
            joinColumns = @JoinColumn(name = "sportsman_id"),
            inverseJoinColumns = @JoinColumn(name = "competition_id")
    )
    private List<Competition> competitions = new ArrayList<>();

    @ManyToMany(mappedBy = "prizeWinners")
    private List<Competition> wonCompetitions;

}
