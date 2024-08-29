import java.util.ArrayList;

public class User {
    private String name;
    private String address;
    private String contactInfo;
    private Account account;

    public User(String name, String address, String contactInfo, double initialDeposit) {
        this.name = name;
        this.address = address;
        this.contactInfo = contactInfo;
        this.account = new Account(initialDeposit);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public Account getAccount() {
        return account;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}