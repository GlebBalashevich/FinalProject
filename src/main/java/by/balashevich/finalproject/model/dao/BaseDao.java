package by.balashevich.finalproject.model.dao;

import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.model.entity.Entity;

import java.util.Map;

public interface BaseDao <T extends Entity> {
    boolean add(Map<String, String> userParameters) throws DaoProjectException;

    boolean remove(T t) throws DaoProjectException;

    T update (T t) throws DaoProjectException;

    T findById(long id) throws DaoProjectException;
}
