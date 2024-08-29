import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BankingInformationSystem {
    private JFrame frame;
    private Bank bank;

    public BankingInformationSystem() {
        bank = new Bank();
        frame = new JFrame("Banking System Prototype");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        frame.add(panel, BorderLayout.CENTER);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Banking System");
        titleLabel.setBounds(150, 10, 150, 25);
        panel.add(titleLabel);

        JButton registerButton = new JButton("Register User");
        registerButton.setBounds(100, 50, 200, 25);
        panel.add(registerButton);

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(100, 80, 200, 25);
        panel.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(100, 110, 200, 25);
        panel.add(withdrawButton);

        JButton transferButton = new JButton("Transfer");
        transferButton.setBounds(100, 140, 200, 25);
        panel.add(transferButton);

        JButton statementButton = new JButton("View Statement");
        statementButton.setBounds(100, 170, 200, 25);
        panel.add(statementButton);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        transferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                transfer();
            }
        });

        statementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewStatement();
            }
        });
    }

    private void registerUser() {
        JTextField nameField = new JTextField(5);
        JTextField addressField = new JTextField(5);
        JTextField contactField = new JTextField(5);
        JTextField depositField = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(4, 2));
        myPanel.add(new JLabel("Name:"));
        myPanel.add(nameField);
        myPanel.add(new JLabel("Address:"));
        myPanel.add(addressField);
        myPanel.add(new JLabel("Contact Info:"));
        myPanel.add(contactField);
        myPanel.add(new JLabel("Initial Deposit:"));
        myPanel.add(depositField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Register User", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String address = addressField.getText();
            String contact = contactField.getText();
            double initialDeposit = Double.parseDouble(depositField.getText());

            User user = bank.registerUser(name, address, contact, initialDeposit);
            JOptionPane.showMessageDialog(frame, "User registered successfully. Account Number: " + user.getAccount().getAccountNumber());
        }
    }

    private void deposit() {
        JTextField accountField = new JTextField(5);
        JTextField amountField = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(2, 2));
        myPanel.add(new JLabel("Account Number:"));
        myPanel.add(accountField);
        myPanel.add(new JLabel("Amount to Deposit:"));
        myPanel.add(amountField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Deposit", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int accountNumber = Integer.parseInt(accountField.getText());
            double amount = Double.parseDouble(amountField.getText());

            try {
                bank.deposit(accountNumber, amount);
                JOptionPane.showMessageDialog(frame, "Deposit successful.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
            }
        }
    }

    private void withdraw() {
        JTextField accountField = new JTextField(5);
        JTextField amountField = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(2, 2));
        myPanel.add(new JLabel("Account Number:"));
        myPanel.add(accountField);
        myPanel.add(new JLabel("Amount to Withdraw:"));
        myPanel.add(amountField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Withdraw", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int accountNumber = Integer.parseInt(accountField.getText());
            double amount = Double.parseDouble(amountField.getText());

            try {
                bank.withdraw(accountNumber, amount);
                JOptionPane.showMessageDialog(frame, "Withdrawal successful.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
            }
        }
    }

    private void transfer() {
        JTextField fromAccountField = new JTextField(5);
        JTextField toAccountField = new JTextField(5);
        JTextField amountField = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(3, 2));
        myPanel.add(new JLabel("From Account Number:"));
        myPanel.add(fromAccountField);
        myPanel.add(new JLabel("To Account Number:"));
        myPanel.add(toAccountField);
        myPanel.add(new JLabel("Amount to Transfer:"));
        myPanel.add(amountField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Transfer", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int fromAccount = Integer.parseInt(fromAccountField.getText());
            int toAccount = Integer.parseInt(toAccountField.getText());
            double amount = Double.parseDouble(amountField.getText());

            try {
                bank.transfer(fromAccount, toAccount, amount);
                JOptionPane.showMessageDialog(frame, "Transfer successful.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
            }
        }
    }

    private void viewStatement() {
        JTextField accountField = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(1, 2));
        myPanel.add(new JLabel("Account Number:"));
        myPanel.add(accountField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "View Statement", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int accountNumber = Integer.parseInt(accountField.getText());

            try {
                ArrayList<String> statement = bank.getStatement(accountNumber);
                StringBuilder statementText = new StringBuilder("Account Statement:\n");
                for (String transaction : statement) {
                    statementText.append(transaction).append("\n");
                }
                JOptionPane.showMessageDialog(frame, statementText.toString());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new BankingInformationSystem();
    }
}
