package models;

public class BankAccountUsers {
    private int accountId;
    private int userId;

    public String toString() {
        return this.accountId + " - " + this.userId;
    }

    public BankAccountUsers (int accountId, int userId) {
        this.accountId = accountId;
        this.userId = userId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
