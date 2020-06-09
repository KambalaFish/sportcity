package com.sportcity.demo.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sport_facilities")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Sport_facilities extends AbstractEntity<Long>{
    /*
    @Id
    @Access(value = AccessType.PROPERTY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    */

    @ManyToMany(mappedBy = "sports_facility")
    private List<Competition> competitions;

    @OneToOne(mappedBy = "sport_facility", cascade=CascadeType.ALL)
    private Court court;

    @OneToOne(mappedBy = "sport_facility", cascade=CascadeType.ALL)
    private Stadium stadium;

    @OneToOne(mappedBy = "sport_facility", cascade=CascadeType.ALL)
    private Volleyball_arena volleyball_arena;

    @OneToOne(mappedBy = "sport_facility", cascade=CascadeType.ALL)
    private Ice_arena ice_arena;
}
