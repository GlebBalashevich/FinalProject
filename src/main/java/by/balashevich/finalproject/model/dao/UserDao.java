package by.balashevich.finalproject.model.dao;

import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.model.entity.User;

import java.util.Optional;

public interface UserDao extends BaseDao<User>{

    boolean updatePassword(User user, String changingPassword) throws DaoProjectException;

    Optional<User> findByEmail(String targetEmail) throws DaoProjectException;

    String findPasswordByEmail(String targetEmail) throws DaoProjectException;

    String existEmail(String targetEmail) throws DaoProjectException;
}
