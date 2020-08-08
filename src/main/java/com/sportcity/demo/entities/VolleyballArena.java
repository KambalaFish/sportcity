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
public class VolleyballArena extends AbstractEntitySF<Integer>{

    @Column(name = "net_height")
    private Integer net_height;

    @Column(name = "net_width")
    private Integer net_width;

    @OneToOne(cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
    private SportFacility sportFacility;
}
