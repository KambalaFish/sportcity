package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.AbstractDTO;
import com.sportcity.demo.entities.AbstractEntity;

import java.io.Serializable;

public interface IMapper<E extends AbstractEntity<ID>, DTO extends AbstractDTO<ID>, ID extends Serializable> {
    E toEntity(DTO dto);
    DTO toDTO(E entity);
}