package repository;

import models.BaseModel;

import java.util.Optional;

public interface EntityRepository<T extends BaseModel> {
    T save(T entity);

    Optional<T> findById(Long id);

    Optional<T> delete(T entity);
}
