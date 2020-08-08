package com.sportcity.demo.entities;

import com.sportcity.demo.entities.types.CoverageType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "court")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Court extends AbstractEntitySF<Integer>{

    @Column(name="coverage_type", nullable = false, columnDefinition = "enum('grass', 'clay')")
    @Enumerated(EnumType.STRING)
    private CoverageType coverageType;

    @OneToOne(cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
    private SportFacility sportFacility;

}
