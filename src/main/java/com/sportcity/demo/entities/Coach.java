package com.sportcity.demo.entities;

import com.sportcity.demo.entities.types.Sport;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="coach")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Coach extends AbstractEntity<Integer>{

    @Column(name="name", nullable = false)
    private String name;
    @Column(name="sport", nullable = false, columnDefinition = "enum('football', 'tennis', 'hockey', 'volleyball')")
    @Enumerated(EnumType.STRING)
    private Sport sport;

    @ManyToMany(mappedBy = "coaches")
    private List<Sportsman> sportsmen = new ArrayList<>();
}