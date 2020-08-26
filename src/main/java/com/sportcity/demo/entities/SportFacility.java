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
public class SportFacility extends AbstractEntitySF<Integer>{

    @ManyToMany(mappedBy = "sportFacilities")
    private List<Competition> competitions;

    @OneToOne(mappedBy = "sportFacility", cascade=CascadeType.ALL)
    private Court court;

    @OneToOne(mappedBy = "sportFacility", cascade=CascadeType.ALL)
    private Stadium stadium;

    @OneToOne(mappedBy = "sportFacility", cascade=CascadeType.ALL)
    private VolleyballArena volleyballArena;

    @OneToOne(mappedBy = "sportFacility", cascade=CascadeType.ALL)
    private IceArena iceArena;
}
