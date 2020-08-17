package com.sportcity.demo.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "club")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Club extends AbstractEntity<Integer>{

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "club", fetch = FetchType.LAZY)
    private List<Sportsman> sportsmen = new ArrayList<>();
}
