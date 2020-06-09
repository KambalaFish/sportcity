package com.sportcity.demo.entities;

import com.sportcity.demo.entities.types.Coverage_type;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "court")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Court extends AbstractEntity<Long>{

    @Column(name="coverage_type")
    @Enumerated(EnumType.STRING)
    private Coverage_type coverage_type;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
    private Sport_facilities sport_facility;

}
