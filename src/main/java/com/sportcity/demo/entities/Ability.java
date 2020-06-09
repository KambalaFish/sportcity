package com.sportcity.demo.entities;

import com.sportcity.demo.entities.types.Sport;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "abilities")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

public class Ability extends AbstractEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "sportsman_id", nullable = false)
    private Sportsman sportsman;

    @Column(name="sport", nullable = false)
    @Enumerated(EnumType.STRING)
    private Sport sport;

    @Column(name = "category")
    private int level;

}
