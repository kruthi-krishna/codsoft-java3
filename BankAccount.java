import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class ATM {
    private JFrame frame;
    private JTextField amountField;
    private JButton withdrawButton;
    private JButton depositButton;
    private JButton checkBalanceButton;
    private JLabel messageLabel;
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;

        // Set up the frame
        frame = new JFrame("ATM Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null);

        // Set up the input field for amount
        JLabel amountLabel = new JLabel("Enter Amount:");
        amountLabel.setBounds(50, 50, 100, 25);
        frame.add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(150, 50, 100, 25);
        frame.add(amountField);

        // Set up the withdraw button
        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(50, 100, 100, 25);
        frame.add(withdrawButton);

        // Set up the deposit button
        depositButton = new JButton("Deposit");
        depositButton.setBounds(200, 100, 100, 25);
        frame.add(depositButton);

        // Set up the check balance button
        checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.setBounds(125, 150, 150, 25);
        frame.add(checkBalanceButton);

        // Set up the message label
        messageLabel = new JLabel("");
        messageLabel.setBounds(50, 200, 300, 25);
        frame.add(messageLabel);

        // Add action listeners to the buttons
        withdrawButton.addActionListener(new WithdrawButtonListener());
        depositButton.addActionListener(new DepositButtonListener());
        checkBalanceButton.addActionListener(new CheckBalanceButtonListener());

        // Make the frame visible
        frame.setVisible(true);
    }

    private class WithdrawButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (account.withdraw(amount)) {
                    messageLabel.setText("Withdrawal successful. New balance: $" + account.getBalance());
                } else {
                    messageLabel.setText("Withdrawal failed. Check amount and balance.");
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Please enter a valid amount.");
            }
        }
    }

    private class DepositButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (account.deposit(amount)) {
                    messageLabel.setText("Deposit successful. New balance: $" + account.getBalance());
                } else {
                    messageLabel.setText("Deposit failed. Enter a valid amount.");
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Please enter a valid amount.");
            }
        }
    }

    private class CheckBalanceButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            messageLabel.setText("Current balance: $" + account.getBalance());
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.00); // Initial balance of $1000
        new ATM(account);
    }
}
