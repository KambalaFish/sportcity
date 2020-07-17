package com.sportcity.demo.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "organizer")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Organizer extends AbstractEntity<Integer>{

    @Column(name="name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "organizers")
    private List<Competition> competitions;
}
