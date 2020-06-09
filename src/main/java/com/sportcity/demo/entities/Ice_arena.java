package com.sportcity.demo.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ice_arena")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Ice_arena extends AbstractEntity<Long>{

    @Column(name = "square")
    private Integer square;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
    private Sport_facilities sport_facility;
}
