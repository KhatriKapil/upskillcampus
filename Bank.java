import java.util.HashMap;
import java.util.ArrayList;

public class Bank {
    private HashMap<Integer, User> users;

    public Bank() {
        this.users = new HashMap<>();
    }

    public User registerUser(String name, String address, String contactInfo, double initialDeposit) {
        User newUser = new User(name, address, contactInfo, initialDeposit);
        users.put(newUser.getAccount().getAccountNumber(), newUser);
        return newUser;
    }

    public User getUser(int accountNumber) {
        return users.get(accountNumber);
    }

    public void deposit(int accountNumber, double amount) throws Exception {
        User user = getUser(accountNumber);
        if (user == null) {
            throw new Exception("User not found");
        }
        user.getAccount().deposit(amount);
    }

    public void withdraw(int accountNumber, double amount) throws Exception {
        User user = getUser(accountNumber);
        if (user == null) {
            throw new Exception("User not found");
        }
        user.getAccount().withdraw(amount);
    }

    public void transfer(int fromAccount, int toAccount, double amount) throws Exception {
        User fromUser = getUser(fromAccount);
        User toUser = getUser(toAccount);
        if (fromUser == null || toUser == null) {
            throw new Exception("User not found");
        }
        fromUser.getAccount().transfer(toUser.getAccount(), amount);
    }

    public ArrayList<String> getStatement(int accountNumber) throws Exception {
        User user = getUser(accountNumber);
        if (user == null) {
            throw new Exception("User not found");
        }
        return user.getAccount().getTransactionHistory();
    }
}
