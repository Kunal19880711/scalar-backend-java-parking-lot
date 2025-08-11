package repository.impl;

import models.BaseModel;
import repository.EntityRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class EntityRepositoryImpl<T extends BaseModel> implements EntityRepository<T> {
    private final Map<Long, T> storage;
    private long nextId = 1L;

    public EntityRepositoryImpl() {
        this.storage = new HashMap<>();
    }

    @Override
    public T save(T entity) {
        if (entity.getId() == 0) {
            entity.setId(nextId++);
        }
        Date now = new Date();
        entity.setUpdatedAt(now);
        return storage.compute(entity.getId(), (id, existingEntity) -> {
            entity.setCreatedAt(existingEntity == null ? now : existingEntity.getCreatedAt());
            return entity;
        });
    }

    @Override
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public Optional<T> delete(T entity) {
        return Optional.ofNullable(storage.remove(entity.getId()));
    }
}
