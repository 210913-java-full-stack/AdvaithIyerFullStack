package DAO;

import models.BankAccount;
import models.BankAccountUsers;
import utils.datastructures.MyArrayList;

import java.sql.*;
import java.util.Scanner;

/**
 * This class is used to access data from the accounts_users table in the database
 */
public class AccountUsersDao implements AccountUsersCrud{

    private Connection conn;

    public AccountUsersDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(BankAccountUsers accountUsers) throws SQLException {
        String insertStatement = "INSERT INTO accounts_users (account_id, user_id) VALUES (?, ?)";
        PreparedStatement preparedInsertStatement = conn.prepareStatement(insertStatement);
        preparedInsertStatement.setInt(1, accountUsers.getAccountId());
        preparedInsertStatement.setInt(2, accountUsers.getUserId());


        preparedInsertStatement.executeUpdate();
    }

    @Override
    public void update(BankAccountUsers accountUsers) throws SQLException {
        Scanner sc = new Scanner(System.in);
        int num = Integer.parseInt(String.valueOf(sc));
        String updateStatement = "UPDATE accounts_users SET account_id = ?, user_id = ? WHERE junction_id = ?";
        PreparedStatement preparedUpdateStatement = conn.prepareStatement(updateStatement);
        preparedUpdateStatement.setInt(1, accountUsers.getAccountId());
        preparedUpdateStatement.setInt(2, accountUsers.getUserId());
        preparedUpdateStatement.setInt(3, num);

        preparedUpdateStatement.executeUpdate();
    }

    @Override
    public MyArrayList<Integer> getAccountUsersByUserId(int id) throws SQLException {
        String sql = "SELECT * FROM accounts_users WHERE user_id = ?";
        PreparedStatement prepStmt = conn.prepareStatement(sql);
        prepStmt.setInt(1, id);

        ResultSet rs = prepStmt.executeQuery();
        MyArrayList<Integer> arrayList = new MyArrayList<>();

        while (rs.next()) {
            arrayList.add(rs.getInt("account_id"));
        }
        return arrayList;

    }

    @Override
    public MyArrayList getAllAccountsUsers() throws SQLException {
        String sql = "SELECT * FROM accounts_users";
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        MyArrayList<BankAccountUsers> resultList = new MyArrayList<>();

        while(rs.next()) {
            BankAccountUsers newItem = new BankAccountUsers(rs.getInt("account_id"),  rs.getInt("user_id"));
            resultList.add(newItem);
        }

        return resultList;
    }

    @Override
    public void deleteByJunctionID(int id) throws SQLException {
        String sql = "DELETE FROM user WHERE junction_id = ?";
        PreparedStatement prepStmt = conn.prepareStatement(sql);
        prepStmt.setInt(1, id);

        prepStmt.executeUpdate();
    }
}
