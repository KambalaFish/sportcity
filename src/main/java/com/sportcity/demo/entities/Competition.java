package com.sportcity.demo.entities;

import com.sportcity.demo.entities.types.Sport;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "competition")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Competition extends AbstractEntity<Integer> {

    @Column(name = "name")
    private String name;

    @Column(name = "beginning_date")
    private Date beginningDate;

    @Column(name = "finish_date")
    private Date finishDate;

    @Column(name="sport", nullable = false, columnDefinition = "enum('football', 'tennis', 'hockey', 'volleyball')")
    @Enumerated(EnumType.STRING)
    private Sport sport;

    @ManyToMany(mappedBy = "competitions")
    private List<Sportsman> sportsmen;

    @ManyToMany
    @JoinTable(
            name = "arrangement",
            joinColumns = @JoinColumn(name = "competition_id"),
            inverseJoinColumns = @JoinColumn(name = "organizer_id")
    )
    private List<Organizer> organizers;

    @ManyToMany
    @JoinTable(
            name = "location",
            joinColumns = @JoinColumn(name = "competition_id"),
            inverseJoinColumns = @JoinColumn(name = "sport_facilities_id")
    )
    private List<SportFacility> sportFacilities;

}
