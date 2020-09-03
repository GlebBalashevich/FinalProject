package by.balashevich.finalproject.model.dao;

import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.model.entity.Entity;

public interface BaseDao <T extends Entity> {
    boolean add(T t) throws DaoProjectException;

    boolean remove(T t) throws DaoProjectException;

    T update (T t) throws DaoProjectException;

    T findById(long id) throws DaoProjectException;
}
