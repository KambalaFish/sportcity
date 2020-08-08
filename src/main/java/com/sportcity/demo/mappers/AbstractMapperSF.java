package com.sportcity.demo.mappers;

import com.sportcity.demo.dtos.AbstractDTO;
import com.sportcity.demo.entities.AbstractEntitySF;
import lombok.Getter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.DestinationSetter;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.Objects;


public class AbstractMapperSF<E extends AbstractEntitySF<ID>, DTO extends AbstractDTO<ID>, ID extends Serializable> implements IMapperSF<E, DTO, ID> {

    @Getter
    protected ModelMapper mapper;

    private final TypeMap<E, DTO> entityToDtoTypeMap;
    private final TypeMap<DTO, E> dtoToEntityTypeMap;

    private Class<E> entityClass;
    private Class<DTO> DTOClass;


    protected AbstractMapperSF(ModelMapper mapper, Class<E> entityClass, Class<DTO> DTOClass){
        this.mapper = mapper;
        this.entityClass = entityClass;
        this.DTOClass = DTOClass;

        entityToDtoTypeMap = mapper.createTypeMap(entityClass, DTOClass).setPostConverter(toDTOConverter());
        dtoToEntityTypeMap = mapper.createTypeMap(DTOClass, entityClass).setPostConverter(toEntityConverter());
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

    protected <V> void skipDTOField(DestinationSetter<DTO, V> destinationSetter){
        entityToDtoTypeMap.addMappings(m -> m.skip(destinationSetter));
    }

    protected <V> void skipEntityField(DestinationSetter<E,V> destinationSetter){
        dtoToEntityTypeMap.addMappings(m -> m.skip(destinationSetter));
    }

    protected <X, I> X getEntityByIdOrThrow(JpaRepository<X, I> repository, I id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Entity with id %s was not found", id)));
    }

    @Override
    public E toEntity(DTO dto) {
        return Objects.isNull(dto)? null : mapper.map(dto, entityClass);
    }

    @Override
    public DTO toDTO(E entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, DTOClass);
    }

}
