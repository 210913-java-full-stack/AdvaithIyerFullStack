package DAO;

import models.BankAccount;
import utils.datastructures.MyArrayList;
import java.sql.*;

/**
 * This class is used to access data from the account table in the database
 */
public class AccountDao implements AccountCrud{

    private Connection conn;

    public AccountDao(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void insert(BankAccount account) throws SQLException {
        String insertStatement = "INSERT INTO accounts (account_id, account_type, balance) VALUES (?, ?, ?)";
        PreparedStatement preparedInsertStatement = conn.prepareStatement(insertStatement);
        preparedInsertStatement.setInt(1, account.getAccountId());
        preparedInsertStatement.setString(2, account.getAccountType());
        preparedInsertStatement.setDouble(3, account.getBalance());

        preparedInsertStatement.executeUpdate();
    }

    @Override
    public void update(BankAccount account) throws SQLException {
        String updateStatement = "UPDATE accounts SET account_type = ?, balance = ? WHERE account_id = ?";
        PreparedStatement preparedUpdateStatement = conn.prepareStatement(updateStatement);
        preparedUpdateStatement.setString(1, account.getAccountType());
        preparedUpdateStatement.setDouble(2, account.getBalance());
        preparedUpdateStatement.setInt(3, account.getAccountId());

        preparedUpdateStatement.executeUpdate();
    }

    @Override
    public BankAccount getAccountByAccountID(int accountId) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE account_id = ?";
        PreparedStatement prepStmt = conn.prepareStatement(sql);
        prepStmt.setInt(1, accountId);

        ResultSet rs = prepStmt.executeQuery();
        if (rs.next()) {
            return new BankAccount(rs.getInt("account_id"), rs.getString("account_type"), rs.getDouble("balance"));
        } else {
            return null;
        }
    }

    @Override
    public MyArrayList getAllAccounts() throws SQLException {
        String sql = "SELECT * FROM accounts";
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        MyArrayList<BankAccount> resultList = new MyArrayList<>();

        while(rs.next()) {
            BankAccount newItem = new BankAccount(rs.getInt("account_id"), rs.getString("account_type"), rs.getDouble("balance"));
            resultList.add(newItem);
        }

        return resultList;
    }

    @Override
    public void deposit(double balance, int id) throws SQLException {
        String sql = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";
        PreparedStatement prepStmt = conn.prepareStatement(sql);
        prepStmt.setDouble(1, balance);
        prepStmt.setInt(2, id);

        prepStmt.executeUpdate();
    }

    @Override
    public void withdraw(double balance, int id) throws SQLException {
        String sql = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";
        PreparedStatement prepStmt = conn.prepareStatement(sql);
        prepStmt.setDouble(1, balance);
        prepStmt.setInt(2, id);

        prepStmt.executeUpdate();
    }

    @Override
    public void deleteByAccountID(int id) throws SQLException {
        String sql = "DELETE FROM accounts WHERE account_id = ?";
        PreparedStatement prepStmt = conn.prepareStatement(sql);
        prepStmt.setInt(1, id);

        prepStmt.executeUpdate();
    }

    public void close() throws SQLException {
        conn.close();
    }
}
