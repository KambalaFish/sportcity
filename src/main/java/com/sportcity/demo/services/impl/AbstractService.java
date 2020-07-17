package com.sportcity.demo.services.impl;

import com.sportcity.demo.dtos.AbstractDTO;
import com.sportcity.demo.entities.AbstractEntity;
import com.sportcity.demo.mappers.IMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import com.sportcity.demo.services.Service;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class AbstractService
        <E extends AbstractEntity<ID>,
        DTO extends AbstractDTO<ID>,
        ID extends Serializable>
        implements Service<DTO, ID> {

    protected abstract JpaRepository<E, ID> getRepository();
    protected abstract IMapper<E, DTO, ID> getMapper();

    @Override
    public long countAll() {
        return getRepository().count();
    }

    @Override
    public DTO getById(ID id) {
        E entity = getEntityByIdOrThrow(id);
        return getMapper().toDTO(entity);
    }

    @Override
    public Page<DTO> getAll(Pageable pageable) {
        return getRepository().findAll(pageable).map(getMapper()::toDTO);
    }

    @Override
    public Collection<DTO> getAllById(Collection<ID> idCollection) {
        return getRepository().findAllById(idCollection).stream().map(getMapper()::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DTO create(DTO dto) {
        var entity = getMapper().toEntity(dto);
        entity = getRepository().save(entity);
        return getMapper().toDTO(entity);
    }

    @Override
    public DTO save(ID id, DTO dto) {
        dto.setId(id);
        return create(dto);
    }

    @Override
    public Collection<DTO> saveAll(Collection<DTO> dtoCollection) {
        var entityCollection = dtoCollection.stream().map(getMapper()::toEntity).collect(Collectors.toList());
        return getRepository().saveAll(entityCollection).stream().map(getMapper()::toDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }

    @Override
    public void deleteAllById(Collection<ID> idCollection) {
        var entityCollection = idCollection.stream().map(getRepository()::getOne).collect(Collectors.toList());
        getRepository().deleteAll(entityCollection);
    }

    protected E getEntityByIdOrThrow(ID id) {
        return getRepository().findById(id).orElseThrow(() ->
                new EntityNotFoundException(
                        String.format("Entity with id '%s' was not found", id)
                )
        );
    }

    protected String prepareStringToLikeStatement(String stringValue) {
        return stringValue == null ?
                null : String.format("%%%s%%", stringValue.toLowerCase());
    }
}
