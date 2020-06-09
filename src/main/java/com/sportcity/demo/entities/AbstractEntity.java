package com.sportcity.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractEntity<ID extends Serializable> implements Serializable {
    @Id
    @Access(value = AccessType.PROPERTY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private ID id;
}
