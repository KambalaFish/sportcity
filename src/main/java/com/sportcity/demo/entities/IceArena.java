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
public class IceArena extends AbstractEntitySF<Integer>{


    @Column(name = "square")
    private Integer square;

    @OneToOne(cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
    private SportFacility sportFacility;
}
