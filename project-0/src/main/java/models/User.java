package models;

public class User {
    private int userId;
    private String name;
    private String email;
    private String password;
    private int addressId;

    /**
     * @return allows for this class to return its data as a string, which is useful for when it is called somewhere else
     */
    public String toString() {
        return this.userId + " - " + this.name + " - " + this.email + " - " + this.password + " - " + this.addressId;
    }

    public User (int userId, String name, String email, String password, int addressId) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.addressId = addressId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
}
