package by.balashevich.finalproject.model.service.impl;

import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.dao.impl.ClientDaoImpl;
import by.balashevich.finalproject.model.entity.Client;
import by.balashevich.finalproject.model.service.UserService;
import by.balashevich.finalproject.util.PasswordEncryption;

import java.security.NoSuchAlgorithmException;

public class ClientServiceImpl implements UserService<Client> {
    @Override
    public boolean add(Client client, String userPassword) throws ServiceProjectException {
        ClientDaoImpl clientDao = new ClientDaoImpl();
        boolean isClientAdded = false;

        try {
            String encryptedPassword = PasswordEncryption.encryptPassword(userPassword);
            isClientAdded = clientDao.add(client, encryptedPassword);
        } catch (DaoProjectException | NoSuchAlgorithmException e) {
            throw new ServiceProjectException("Error occurred during adding Client into database", e);
        }

        return isClientAdded;
    }

    @Override
    public boolean remove(Client client) throws ServiceProjectException {
        return false;
    }

    @Override
    public Client update(Client client) throws ServiceProjectException {
        return null;
    }
}
