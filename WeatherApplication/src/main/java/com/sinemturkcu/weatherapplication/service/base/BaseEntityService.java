package com.sinemturkcu.weatherapplication.service.base;


import com.sinemturkcu.weatherapplication.entity.base.BaseAdditionalFields;
import com.sinemturkcu.weatherapplication.entity.base.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public abstract class BaseEntityService <E extends BaseEntity, R extends JpaRepository<E,Long>> {

    private final R repository;

    public List<E> getAll() {
        return repository.findAll();
    }

    public E save(E entity) {
        BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();

        if (baseAdditionalFields == null) {
            baseAdditionalFields = new BaseAdditionalFields();
        }

        if (entity.getId() == null) {
            baseAdditionalFields.setCreatedDate(LocalDateTime.now());
        }

        baseAdditionalFields.setUpdatedDate(LocalDateTime.now());
        entity.setBaseAdditionalFields(baseAdditionalFields);
        return repository.save(entity);
    }

    public E getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public void delete(E e) {
        repository.delete(e);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}