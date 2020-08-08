package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.AbstractDTO;
import com.sportcity.demo.entities.AbstractEntitySF;

import java.io.Serializable;

public interface IMapperSF<E extends AbstractEntitySF<ID>, DTO extends AbstractDTO<ID>, ID extends Serializable> {
    E toEntity(DTO dto);
    DTO toDTO(E entity);
}