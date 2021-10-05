package models;

public class BankAccountUsers {
    private int accountId;
    private int userId;

    /**
     * @return allows for this class to return its data as a string, which is useful for when it is called somewhere else
     */
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
