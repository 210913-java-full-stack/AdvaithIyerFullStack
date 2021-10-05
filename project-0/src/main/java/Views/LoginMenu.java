package Views;

import DAO.AccountDao;
import DAO.AccountUsersDao;
import models.BankAccount;
import utils.ConnectionManager;
import utils.datastructures.MyArrayList;


import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

/**
 * This is run if you select option 2 on the Main Menu page
 * This is where you already want to pick from existing accounts to either view, deposit, or withdraw
 */

public class LoginMenu {

    public static Scanner sc = new Scanner(System.in);
    public static boolean running = true;
    private static int userId;

    public static void run() {

        /**
         * Employs boolean to run the While-loop
         * Uses Scanner to pick options
         */
        while (running) {
            int option = loginScreen();

            switch (option) {
                case 1:
                    try {
                        System.out.println("Input your userId");
                        String text = sc.nextLine();
                        userId = Integer.parseInt(text);
                        viewAccounts(userId);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    RegisterMenu.run();
                    running = false;
                    break;
                case 3:
                    try{
                      deposit();
                      System.out.println("You have made a successful deposit!");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 4:
                    try{
                        withdraw();
                        System.out.println("You have made a successful withdrawal!");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 5:
                    running = false;
                    MainMenu.run();
                    break;
                default: System.out.println("Input invalid. Please enter a number between 1 and 5.");
                    break;
            }
        }
    }
    public static int loginScreen() {
        System.out.printf("\n------------------------------------\n"
                + "\nLogin Menu\n"
                + "\n------------------------------------\n\n");

        System.out.printf("1. View Accounts\n"
                + "2. Create new account\n"
                + "3. Deposit money\n"
                + "4. Withdraw money\n"
                + "5. Logout\n\n") ;

        int option = 0;

        try {
            String text = sc.nextLine();
            option = Integer.parseInt(text);
        }catch(Exception ex){
            System.out.println("Caught exception");
        }

        return option;
    }

    /**
     * This is used to view accountId and balance
     * The Locale and NumberFormat classes are used to print the balance in a US currency format
     * Uses a For-Loop to iterate
     * ArrayList is the type for accountsUsers as 1 user can have multiple accounts
     */

    public static void viewAccounts(int userId) throws SQLException {
        AccountUsersDao accountUsersDao = new AccountUsersDao(ConnectionManager.getConnection());
        MyArrayList<Integer> accounts = accountUsersDao.getAccountUsersByUserId(userId);
        AccountDao accountDao = new AccountDao(ConnectionManager.getConnection());

        for (int i = 0; i < accounts.size(); i++) {
            int accountId = accounts.get(i);
            BankAccount account = accountDao.getAccountByAccountID(accountId);
            double balance = account.getBalance();
            Locale usa = new Locale("en", "US");
            NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);
            String format = dollarFormat.format(balance);
            System.out.println("Account Id: " + accountId + " Balance: " + format);
        }

    }


    public static void deposit() throws SQLException {

        int deposit = 0;
        int accountId = 0;

        try{
            System.out.println("Input amount you want to deposit: ");
            String text = sc.nextLine();
            deposit = Integer.parseInt(text);
            System.out.println("Input accountId of account you want to deposit to: ");
            String text2 = sc.nextLine();
            accountId = Integer.parseInt(text2);
        } catch (Exception ex) {
            System.out.println("Exception has been caught");
        }
        AccountDao accountDao = new AccountDao(ConnectionManager.getConnection());
        accountDao.deposit(deposit, accountId);
    }

    public static void withdraw() throws SQLException {
        int withdraw = 0;
        int accountId = 0;

        try{
            System.out.println("Input amount you want to withdraw: ");
            String text = sc.nextLine();
            withdraw = Integer.parseInt(text);
            System.out.println("Input accountId of account you want to withdraw from: ");
            String text2 = sc.nextLine();
            accountId = Integer.parseInt(text2);
        } catch (Exception ex) {
            System.out.println("Exception has been caught");
        }
        AccountDao accountDao = new AccountDao(ConnectionManager.getConnection());
        accountDao.withdraw(withdraw, accountId);
    }
}
