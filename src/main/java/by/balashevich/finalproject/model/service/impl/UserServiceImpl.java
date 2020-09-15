package by.balashevich.finalproject.model.service.impl;

import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.dao.impl.UserDaoImpl;
import by.balashevich.finalproject.model.entity.User;
import by.balashevich.finalproject.model.service.UserService;
import by.balashevich.finalproject.util.PasswordEncryption;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class UserServiceImpl implements UserService<User> {
    @Override
    public boolean add(User user, String userPassword) throws ServiceProjectException {
        return false;
    }

    @Override
    public boolean remove(User user) throws ServiceProjectException{
        return false;
    }

    @Override
    public User update(User user) throws ServiceProjectException{
        return null;
    }

    public Optional<User> findUserByLogin(String login) throws ServiceProjectException {
        UserDaoImpl userDao = new UserDaoImpl();
        Optional<User> targetUser;

        try{
            targetUser = userDao.findByLogin(login);
        } catch (DaoProjectException e) {
            throw new ServiceProjectException("An error occurred during searching user by login", e);
        }

        return targetUser;
    }

    public boolean verifyUser(String login, String password) throws ServiceProjectException {
        UserDaoImpl userDao = new UserDaoImpl();
        boolean isApproved = false;

        try{
            String userPassword = userDao.findPasswordByLogin(login);
            if (userPassword != null && !userPassword.isEmpty()){
                String verifyingPassword = PasswordEncryption.encryptPassword(password);
                if (userPassword.equals(verifyingPassword)){
                    isApproved = true;
                }
            }
        } catch (DaoProjectException | NoSuchAlgorithmException e){
            throw new ServiceProjectException("An error occurred while verifying user", e);
        }

        return isApproved;
    }
}
