package DAO;

import models.User;
import utils.datastructures.MyArrayList;

import java.sql.SQLException;
import java.util.Scanner;

public interface UserCrud<T> {
    public void insert(User user) throws SQLException;
    public void update(User user) throws SQLException;
    public User getUserByUserId(int id) throws SQLException;
    public MyArrayList<T> getAllUsers() throws SQLException;
    public void deleteByUserID(int id) throws SQLException;
}
