package by.balashevich.finalproject.model.service;

import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.Client;
import by.balashevich.finalproject.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface User service.
 *
 * @author Balashevich Gleb
 * @version 1.0
 */
public interface UserService {
    /**
     * Add boolean.
     *
     * @param user the user
     * @return the boolean
     * @throws ServiceProjectException the service project exception
     */
    boolean add(Map<String, String> user) throws ServiceProjectException;

    /**
     * Update client status boolean.
     *
     * @param email the email
     * @return the boolean
     * @throws ServiceProjectException the service project exception
     */
    boolean activateClient(String email) throws ServiceProjectException;

    /**
     * Update client status boolean.
     *
     * @param updatingClient the updating client
     * @param statusData     the status data
     * @return the boolean
     * @throws ServiceProjectException the service project exception
     */
    boolean updateClientStatus(Client updatingClient, String statusData) throws ServiceProjectException;

    /**
     * Find user by email optional.
     *
     * @param email the email
     * @return the optional
     * @throws ServiceProjectException the service project exception
     */
    Optional<User> findUserByEmail(String email) throws ServiceProjectException;

    /**
     * Find clients by status list.
     *
     * @param clientStatusData the client status data
     * @return the list
     * @throws ServiceProjectException the service project exception
     */
    List<Client> findClientsByStatus(String clientStatusData) throws ServiceProjectException;

    /**
     * Authorize user boolean.
     *
     * @param email    the email
     * @param password the password
     * @return the boolean
     * @throws ServiceProjectException the service project exception
     */
    boolean authorizeUser(String email, String password) throws ServiceProjectException;

    /**
     * Exist user boolean.
     *
     * @param email the email
     * @return the boolean
     * @throws ServiceProjectException the service project exception
     */
    boolean existUser(String email) throws ServiceProjectException;
}
