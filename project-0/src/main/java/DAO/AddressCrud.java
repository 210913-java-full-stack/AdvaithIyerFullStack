package DAO;

import models.Address;
import utils.datastructures.MyArrayList;

import java.sql.SQLException;
import java.util.Scanner;

public interface AddressCrud<T> {

    Scanner sc = new Scanner(System.in);

    public void insert(Address address) throws SQLException;
    public void update(Scanner sc) throws SQLException;
    public Address getAddressByAddressID(int id) throws SQLException;
    public MyArrayList<T> getAllAddresses() throws SQLException;
    public void deleteByAddressID(int id) throws SQLException;
}
