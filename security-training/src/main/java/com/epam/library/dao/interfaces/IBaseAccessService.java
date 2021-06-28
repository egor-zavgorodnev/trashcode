package com.epam.library.dao.interfaces;

public interface IBaseAccessService<T> {
    void addNewEntity(T entity);
    void deleteEntity(T entity);
}
