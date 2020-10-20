package by.balashevich.finalproject.model.dao;

import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.model.entity.Entity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BaseDao<T extends Entity> {
    boolean add(Map<String, Object> parameters) throws DaoProjectException;

    boolean remove(T t) throws DaoProjectException;

    boolean update(T t) throws DaoProjectException;

    Optional<T> findById(long id) throws DaoProjectException;

    List<T> findAll() throws DaoProjectException;
}
