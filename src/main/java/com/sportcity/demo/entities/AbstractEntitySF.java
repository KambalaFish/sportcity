package com.sportcity.demo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public class AbstractEntitySF<ID extends Serializable> implements Serializable {

    @Id
    @Access(value = AccessType.PROPERTY)
    @Column(name = "id", nullable = false)
    private ID id;

}
