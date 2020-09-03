package by.balashevich.finalproject.model.service;

import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.User;

import java.util.Optional;

public interface UserService {
    boolean add(User user) throws ServiceProjectException;

    boolean remove(User user) throws ServiceProjectException;

    User update(User user) throws ServiceProjectException;

    Optional<User> verifyUser(String login, String password) throws ServiceProjectException;
}
