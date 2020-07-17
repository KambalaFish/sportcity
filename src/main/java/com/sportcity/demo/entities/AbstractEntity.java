package com.sportcity.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@MappedSuperclass
@Getter @Setter
public abstract class AbstractEntity<ID extends Serializable> implements Serializable {
    @Id
    @Access(value = AccessType.PROPERTY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private ID id;
}
