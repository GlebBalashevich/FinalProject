package by.balashevich.finalproject.model.service.impl;

import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.dao.impl.UserDaoImpl;
import by.balashevich.finalproject.model.entity.User;
import by.balashevich.finalproject.model.service.UserService;
import by.balashevich.finalproject.util.PasswordEncryption;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Override
    public boolean add(User user) throws ServiceProjectException {
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

    @Override
    public Optional<User> verifyUser(String login, String password) throws ServiceProjectException {
        UserDaoImpl userDao = new UserDaoImpl();
        Optional<User> verifyingUser = Optional.empty();

        try{
            Optional<User> user = userDao.findByLogin(login);
            if (user.isPresent()){
                String userPassword = user.get().getPassword();
                String verifyingPassword = PasswordEncryption.encryptPassword(password);
                if (userPassword.equals(verifyingPassword)){
                    verifyingUser = user;
                }
            }
        } catch (DaoProjectException e){
            throw new ServiceProjectException("An error occurred while searching for a user by login", e);
        }
        return verifyingUser;
    }
}
