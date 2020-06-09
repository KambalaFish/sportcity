package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.AbstractDTO;
import com.sportcity.demo.entities.AbstractEntity;
import lombok.Getter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.HashMap;

@Getter
public class AbstractMapper<E extends AbstractEntity<ID>, DTO extends AbstractDTO<ID>, ID extends Serializable> implements IMapper<E, DTO, ID> {

    public static final HashMap<Class, AbstractMapper> mappers = new HashMap<>();

    protected ModelMapper mapper;
    private Class<E> entityClass;
    private Class<DTO> DTOClass;


    protected AbstractMapper(ModelMapper mapper, Class<E> entityClass, Class<DTO> DTOClass){
        this.mapper = mapper;
        this.entityClass = entityClass;
        this.DTOClass = DTOClass;
        mappers.put(entityClass, this);
    }

    public Converter<E, DTO> toDTOConverter() {
        return context -> {
            E source = context.getSource();
            DTO destination = context.getDestination();
            mapEntityToDTO(source, destination);
            return destination;
        };
    }

    public Converter<DTO, E> toEntityConverter() {
        return context -> {
            DTO source = context.getSource();
            E destination = context.getDestination();
            mapDTOToEntity(source, destination);
            return destination;
        };
    }

    protected void mapEntityToDTO(E entity, DTO DTO){

    }

    protected void mapDTOToEntity(DTO DTO, E entity){

    }

    @Override
    public E toEntity(DTO dto) {
        return dto == null ? null : mapper.map(dto, entityClass);
    }

    @Override
    public DTO toDTO(E entity) {
        return entity == null ? null : mapper.map(entity, DTOClass);
    }
}
