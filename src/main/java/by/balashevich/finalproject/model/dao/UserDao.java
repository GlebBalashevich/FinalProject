package by.balashevich.finalproject.model.dao;

import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.model.entity.Client;
import by.balashevich.finalproject.model.entity.User;

import java.util.Optional;

public interface UserDao extends BaseDao<User> {

    boolean updateClientStatus(String targetEmail, Client.Status status) throws DaoProjectException;

    Optional<User> findByEmail(String targetEmail) throws DaoProjectException;

    Client.Status findStatusByEmail(String targetEmail) throws DaoProjectException;

    String findPasswordByEmail(String targetEmail) throws DaoProjectException;

    String existEmail(String targetEmail) throws DaoProjectException;
}
