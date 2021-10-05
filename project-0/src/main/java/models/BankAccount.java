package models;

public class BankAccount {

    protected int accountId;
    protected String accountType;
    protected double balance;

    /**
     * @return allows for this class to return its data as a string
     */
    public String toString() {
        return this.accountId + " - " + this.accountType + " - " + this.balance;
    }

    public BankAccount (int accountId, String accountType, double balance) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        try {
            if (accountType.equalsIgnoreCase("Checking")) {
                this.accountType = "Checking";
            }
            if(accountType.equalsIgnoreCase("Savings")) {
                this.accountType = "Savings";
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
