package com.sportcity.demo.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "stadium")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Stadium extends AbstractEntitySF<Integer>{
    /*
    @Id
    @Column(name = "stadium_id")
    private Long stadium_id;
    */

    @Column(name = "capacity")
    private Integer capacity;

    /*
    @OneToOne
    @PrimaryKeyJoinColumn(name = "stadium_id", referencedColumnName = "facility_id")
    private Sport_facilities sport_facility;
    */
    @OneToOne(cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
    private SportFacility sportFacility;
}
