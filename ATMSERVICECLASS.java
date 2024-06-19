import java.util.HashMap;
import java.util.Map;

public class ATMService {
    private Map<String, User> users;
    private User authenticatedUser;

    public ATMService() {
        users = new HashMap<>();
        // Adding some users for demonstration purposes
        users.put("user1", new User("user1", "1234"));
        users.put("user2", new User("user2", "5678"));
    }

    public boolean authenticateUser(String userId, String pin) {
        User user = users.get(userId);
        if (user != null && user.getPin().equals(pin)) {
            authenticatedUser = user;
            return true;
        } else {
            return false;
        }
    }

    public void viewTransactionHistory() {
        if (authenticatedUser != null) {
            System.out.println("Transaction History:");
            for (Transaction transaction : authenticatedUser.getAccount().getTransactionHistory()) {
                System.out.println(transaction);
            }
        }
    }

    public void withdraw(double amount) {
        if (authenticatedUser != null) {
            Account account = authenticatedUser.getAccount();
            if (account.withdraw(amount)) {
                System.out.println("Withdrawal successful. New balance: " + account.getBalance());
            } else {
                System.out.println("Insufficient funds.");
            }
        }
    }

    public void deposit(double amount) {
        if (authenticatedUser != null) {
            Account account = authenticatedUser.getAccount();
            account.deposit(amount);
            System.out.println("Deposit successful. New balance: " + account.getBalance());
        }
    }

    public void transfer(String recipientId, double amount) {
        if (authenticatedUser != null) {
            User recipient = users.get(recipientId);
            if (recipient != null) {
                Account senderAccount = authenticatedUser.getAccount();
                if (senderAccount.withdraw(amount)) {
                    recipient.getAccount().deposit(amount);
                    senderAccount.addTransaction("Transfer to " + recipientId, amount);
                    recipient.getAccount().addTransaction("Transfer from " + authenticatedUser.getUserId(), amount);
                    System.out.println("Transfer successful. New balance: " + senderAccount.getBalance());
                } else {
                    System.out.println("Insufficient funds.");
                }
            } else {
                System.out.println("Recipient not found.");
            }
        }
    }
}
