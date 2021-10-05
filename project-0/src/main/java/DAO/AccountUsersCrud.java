package DAO;

import models.BankAccount;
import models.BankAccountUsers;
import utils.datastructures.MyArrayList;

import java.sql.SQLException;

/**
 * @param <T> this is the interface that is implemented by AccountDAO
 *            the generic type is used to enable classes and interfaces as shown below to be parameters
 */
public interface AccountUsersCrud<T> {
    public void insert(BankAccountUsers accountUsers) throws SQLException;
    public void update(BankAccountUsers accountUsers) throws SQLException;
    public MyArrayList<BankAccount> getAccountUsersByUserId(int id) throws SQLException;
    public MyArrayList<T> getAllAccountsUsers() throws SQLException;
    public void deleteByJunctionID(int id) throws SQLException;
}
