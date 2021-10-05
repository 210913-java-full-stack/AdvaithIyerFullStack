package Views;

import models.*;

import java.sql.SQLException;
import java.util.Scanner;
import DAO.*;
import utils.ConnectionManager;

/**
 * This is run after option = 1 in the MainMenu class
 * This is where you can create and register new accounts into the database
 */

public class RegisterMenu {
    public static Scanner sc = new Scanner(System.in);
    public static boolean running = true;
    public static BankAccountUsers model;

    public static void run() {

        while (running) {
            int option = registerScreen();

            switch (option) {
                case 1:
                    try {
                        createAccount(model);
                        System.out.println("You have just registered a bank account!");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    running = false;
                    MainMenu.GoodbyeScreen();
                    break;
                default: System.out.println("Input invalid. Please enter either 1 or 2.");
                    break;
            }
        }
    }

    public static int registerScreen() {
        System.out.printf("\n------------------------------------\n"
                + "\nRegister Menu\n"
                + "\n------------------------------------\n\n");

        System.out.printf("1. Continue to Registration\n"
                        + "2. Quit\n\n") ;

        int option = 0;

        try {
            String text = sc.nextLine();
            option = Integer.parseInt(text);
        }catch(Exception ex){
            System.out.println("Caught exception");
        }

        return option;
    }

    public static void createAccount(BankAccountUsers model) throws SQLException {
        accountUserSetup();
        accountSetup();
        addressSetup();
        userSetup();
    }


    public static void accountUserSetup() throws SQLException {
        int accountId = 0;
        int userId = 0;
        try {
            System.out.println("Input account_id");
            String text = sc.nextLine();
            accountId = Integer.parseInt(text);
            System.out.println("Input user_id");
            String text2 = sc.nextLine();
            userId = Integer.parseInt(text2);
        }catch(Exception ex){
            System.out.println("Caught exception");
        }
        BankAccountUsers model = new BankAccountUsers(accountId, userId);
        AccountUsersDao accountUsersDao = new AccountUsersDao(ConnectionManager.getConnection());
        accountUsersDao.insert(model);
    }

    public static void accountSetup() throws SQLException {
        int accountId = 0;
        String accountType = "";
        double balance = 0.00;
        try {
            System.out.println("Input account_id: ");
            String text = sc.nextLine();
            accountId = Integer.parseInt(text);
            System.out.println("Which type of account: ");
            accountType = sc.nextLine();
            System.out.println("Input starting balance: ");
            String text2 = sc.nextLine();
            balance = Double.parseDouble(text2);
        }catch(Exception ex){
            System.out.println("Caught exception");
        }
        BankAccount account = new BankAccount(accountId, accountType, balance);
        AccountDao accountDao = new AccountDao(ConnectionManager.getConnection());
        accountDao.insert(account);
    }

    public static void addressSetup() throws SQLException {
        String address = "";
        String city = "";
        String state = "";
        int zip = 0;

        try{
            System.out.println("Input street address: ");
            address = sc.nextLine();
            System.out.println("City of residence: ");
            city = sc.nextLine();
            System.out.println("State of residence (two letter abbreviation): ");
            state = sc.nextLine();
            System.out.println("Enter zip code");
            String text4 = sc.nextLine();
            zip = Integer.parseInt(text4);
        } catch (Exception ex) {
            System.out.println("Caught exception");
        }
        Address fullAddress = new Address(address, city, state, zip);
        AddressDao addressDao = new AddressDao(ConnectionManager.getConnection());
        addressDao.insert(fullAddress);
    }

    public static void userSetup() throws SQLException {
        int userId = 0;
        String name = "";
        String email = "";
        String password = "";
        int addressId = 0;

        try{
            System.out.println("Input userId");
            String text = sc.nextLine();
            userId = Integer.parseInt(text);
            System.out.println("What is your name?");
            name = sc.nextLine();
            System.out.println("Please input your email");
            email = sc.nextLine();
            System.out.println("Password: ");
            password = sc.nextLine();
            System.out.println("Input addressId: ");
            String text2 = sc.nextLine();
            addressId = Integer.parseInt(text2);
        } catch (Exception ex) {
            System.out.println("Caught exception");
        }

        User user = new User(userId, name, email, password, addressId);
        UserDao userDao = new UserDao(ConnectionManager.getConnection());
        userDao.insert(user);
    }
}
