package by.balashevich.finalproject.model.dao;

import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.model.entity.Client;
import by.balashevich.finalproject.model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * The interface User dao.
 *
 * @author Balashevich Gleb
 * @version 1.0
 */
public interface UserDao extends BaseDao<User> {

    /**
     * Update client status boolean.
     *
     * @param targetEmail the target email
     * @param status      the status
     * @return the boolean
     * @throws DaoProjectException the dao project exception
     */
    boolean updateClientStatus(String targetEmail, Client.Status status) throws DaoProjectException;

    /**
     * Find by email optional.
     *
     * @param targetEmail the target email
     * @return the optional
     * @throws DaoProjectException the dao project exception
     */
    Optional<User> findByEmail(String targetEmail) throws DaoProjectException;

    /**
     * Find all clients list.
     *
     * @return the list
     * @throws DaoProjectException the dao project exception
     */
    List<Client> findAllClients() throws DaoProjectException;

    /**
     * Find status by email client . status.
     *
     * @param targetEmail the target email
     * @return the client . status
     * @throws DaoProjectException the dao project exception
     */
    Client.Status findStatusByEmail(String targetEmail) throws DaoProjectException;

    /**
     * Find clients by status list.
     *
     * @param clientStatus the client status
     * @return the list
     * @throws DaoProjectException the dao project exception
     */
    List<Client> findClientsByStatus(Client.Status clientStatus) throws DaoProjectException;

    /**
     * Find password by email string.
     *
     * @param targetEmail the target email
     * @return the string
     * @throws DaoProjectException the dao project exception
     */
    String findPasswordByEmail(String targetEmail) throws DaoProjectException;

    /**
     * Exist email string.
     *
     * @param targetEmail the target email
     * @return the string
     * @throws DaoProjectException the dao project exception
     */
    String existEmail(String targetEmail) throws DaoProjectException;
}
