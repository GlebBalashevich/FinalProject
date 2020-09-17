package by.balashevich.finalproject.model.service;

import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.User;

import java.util.Map;

public interface UserService <T extends User> {
    boolean add(Map<String, String> user) throws ServiceProjectException;

    boolean remove(T user) throws ServiceProjectException;

    T update(T user) throws ServiceProjectException;

}
