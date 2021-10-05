package DAO;

import models.Address;
import models.User;
import utils.datastructures.MyArrayList;
import java.sql.*;

/**
 * This class is used to access data from the users table in the database
 */
public class UserDao implements UserCrud{

    private Connection conn;

    public UserDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(User user) throws SQLException {
        String insertStatement = "INSERT INTO users (user_id, name, email, password, address_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedInsertStatement = conn.prepareStatement(insertStatement);
        preparedInsertStatement.setInt(1, user.getUserId());
        preparedInsertStatement.setString(2, user.getName());
        preparedInsertStatement.setString(3, user.getEmail());
        preparedInsertStatement.setString(4, user.getPassword());
        preparedInsertStatement.setInt(5, user.getAddressId());


        preparedInsertStatement.executeUpdate();
    }

    @Override
    public void update(User user) throws SQLException {
        String updateStatement = "UPDATE users SET name = ?, email = ?, password = ?, address_id = ? WHERE user_id = ?";
        PreparedStatement preparedUpdateStatement = conn.prepareStatement(updateStatement);
        preparedUpdateStatement.setString(1, user.getName());
        preparedUpdateStatement.setString(2, user.getEmail());
        preparedUpdateStatement.setString(3, user.getPassword());
        preparedUpdateStatement.setInt(4, user.getAddressId());
        preparedUpdateStatement.setInt(5, user.getUserId());

        preparedUpdateStatement.executeUpdate();
    }

    @Override
    public User getUserByUserId(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        PreparedStatement prepStmt = conn.prepareStatement(sql);
        prepStmt.setInt(1, id);

        ResultSet rs = prepStmt.executeQuery();
        if (rs.next()) {
            return new User(rs.getInt("user_id"), rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getInt("address_id"));
        } else {
            return null;
        }
    }

    @Override
    public MyArrayList getAllUsers() throws SQLException {
        String sql = "SELECT * FROM users";
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        MyArrayList<User> resultList = new MyArrayList<>();

        while(rs.next()) {
            User newItem = new User(rs.getInt("user_id"), rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getInt("address_id"));
            resultList.add(newItem);
        }

        return resultList;
    }

    @Override
    public void deleteByUserID(int id) throws SQLException {
        String sql = "DELETE FROM user WHERE user_id = ?";
        PreparedStatement prepStmt = conn.prepareStatement(sql);
        prepStmt.setInt(1, id);

        prepStmt.executeUpdate();
    }
}
