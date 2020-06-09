package com.sportcity.demo.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "volleyball_arena")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Volleyball_arena extends AbstractEntity<Long>{

    @Column(name = "net_height")
    private Integer net_height;

    @Column(name = "net_width")
    private Integer net_width;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
    private Sport_facilities sport_facility;
}
