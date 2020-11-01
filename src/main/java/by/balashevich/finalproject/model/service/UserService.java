package by.balashevich.finalproject.model.service;

import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.Client;
import by.balashevich.finalproject.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    boolean add(Map<String, String> user) throws ServiceProjectException;

    boolean updateClientStatus(String email, Client.Status status) throws ServiceProjectException;

    boolean updateClientStatus(Client updatingClient, String statusData) throws ServiceProjectException;

    Optional<User> findUserByEmail(String email) throws ServiceProjectException;

    List<Client> findClientsByStatus(String clientStatusData) throws ServiceProjectException;

    boolean authorizeUser(String email, String password) throws ServiceProjectException;

    boolean existUser(String email) throws ServiceProjectException;

}
