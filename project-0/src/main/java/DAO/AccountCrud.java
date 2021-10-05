package DAO;

import models.BankAccount;
import utils.datastructures.MyArrayList;
import java.sql.SQLException;

/**
 * @param <T> this is the interface that is implemented by AccountDAO
 *            the generic type is used to enable classes and interfaces as shown below to be parameters
 */
public interface AccountCrud<T> {
    public void insert(BankAccount account) throws SQLException;
    public void update(BankAccount account) throws SQLException;
    public BankAccount getAccountByAccountID(int id) throws SQLException;
    public MyArrayList<T> getAllAccounts() throws SQLException;
    public void deposit(double balance, int id) throws SQLException;
    public void withdraw(double balance, int id) throws SQLException;
    public void deleteByAccountID(int id) throws SQLException;

}
