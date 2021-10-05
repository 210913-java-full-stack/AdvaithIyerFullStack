package DAO;

import models.Address;
import utils.datastructures.MyArrayList;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * @param <T> this is the interface that is implemented by AccountDAO
 *            the generic type is used to enable classes and interfaces as shown below to be parameters
 */
public interface AddressCrud<T> {

    public void insert(Address address) throws SQLException;
    public void update(Scanner sc) throws SQLException;
    public Address getAddressByAddressID(int id) throws SQLException;
    public MyArrayList<T> getAllAddresses() throws SQLException;
    public void deleteByAddressID(int id) throws SQLException;
}
