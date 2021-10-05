package DAO;

import models.Address;
import models.BankAccount;
import utils.datastructures.MyArrayList;

import java.sql.*;
import java.util.Scanner;

/**
 * This class is used to access data from the address table in the database
 */
public class AddressDao implements AddressCrud{

    private Connection conn;

    public AddressDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Address address) throws SQLException {
        String insertStatement = "INSERT INTO address (address, city, state, zip) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedInsertStatement = conn.prepareStatement(insertStatement);
        preparedInsertStatement.setString(1, address.getAddress());
        preparedInsertStatement.setString(2, address.getCity());
        preparedInsertStatement.setString(3, address.getState());
        preparedInsertStatement.setInt(4, address.getZip());


        preparedInsertStatement.executeUpdate();
    }

    @Override
    public void update(Scanner sc) throws SQLException {
        Address address = new Address();
        int num = Integer.parseInt(String.valueOf(sc));
        String updateStatement = "UPDATE address SET address = ?, city = ?, state = ?, city = ?, zip = ? WHERE account_id = ?";
        PreparedStatement preparedUpdateStatement = conn.prepareStatement(updateStatement);
        preparedUpdateStatement.setString(1, address.getAddress());
        preparedUpdateStatement.setString(2, address.getCity());
        preparedUpdateStatement.setString(3, address.getState());
        preparedUpdateStatement.setInt(4, address.getZip());
        preparedUpdateStatement.setInt(5, num);

        preparedUpdateStatement.executeUpdate();
    }

    @Override
    public Address getAddressByAddressID(int id) throws SQLException {
        String sql = "SELECT * FROM address WHERE address_id = ?";
        PreparedStatement prepStmt = conn.prepareStatement(sql);
        prepStmt.setInt(1, id);

        ResultSet rs = prepStmt.executeQuery();
        if (rs.next()) {
            return new Address(rs.getString("address"), rs.getString("city"), rs.getString("state"), rs.getInt("zip"));
        } else {
            return null;
        }
    }

    @Override
    public MyArrayList getAllAddresses() throws SQLException {
        String sql = "SELECT * FROM address";
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        MyArrayList<Address> resultList = new MyArrayList<>();

        while(rs.next()) {
            Address newItem = new Address(rs.getString("address"), rs.getString("city"), rs.getString("state"), rs.getInt("zip"));
            resultList.add(newItem);
        }

        return resultList;
    }

    @Override
    public void deleteByAddressID(int id) throws SQLException {
        String sql = "DELETE FROM address WHERE address_id = ?";
        PreparedStatement prepStmt = conn.prepareStatement(sql);
        prepStmt.setInt(1, id);

        prepStmt.executeUpdate();
    }

    public void close() throws SQLException {
        conn.close();
    }
}
