import java.util.ArrayList;

public class Account {
    private static int accountCounter = 1000;
    private int accountNumber;
    private double balance;
    private ArrayList<String> transactionHistory;

    public Account(double initialDeposit) {
        this.accountNumber = ++accountCounter;
        this.balance = initialDeposit;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with initial deposit: " + initialDeposit);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposit: " + amount + ", New Balance: " + balance);
    }

    public void withdraw(double amount) throws Exception {
        if (amount > balance) {
            throw new Exception("Insufficient funds");
        }
        balance -= amount;
        transactionHistory.add("Withdrawal: " + amount + ", New Balance: " + balance);
    }

    public void transfer(Account recipient, double amount) throws Exception {
        if (amount > balance) {
            throw new Exception("Insufficient funds");
        }
        this.withdraw(amount);
        recipient.deposit(amount);
        transactionHistory.add("Transfer to Account " + recipient.getAccountNumber() + ": " + amount);
        recipient.getTransactionHistory().add("Transfer from Account " + this.accountNumber + ": " + amount);
    }
}
