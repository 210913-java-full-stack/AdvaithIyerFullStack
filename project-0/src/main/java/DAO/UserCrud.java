package DAO;

import models.User;
import utils.datastructures.MyArrayList;

import java.sql.SQLException;

/**
 * @param <T> this is the interface that is implemented by AccountDAO
 *            the generic type is used to enable classes and interfaces as shown below to be parameters
 */
public interface UserCrud<T> {
    public void insert(User user) throws SQLException;
    public void update(User user) throws SQLException;
    public User getUserByUserId(int id) throws SQLException;
    public MyArrayList<T> getAllUsers() throws SQLException;
    public void deleteByUserID(int id) throws SQLException;
}
