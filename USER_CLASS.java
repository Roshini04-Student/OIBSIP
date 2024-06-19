import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String pin;
    private List<Account> accounts;

    public User(String userId, String pin) {
        this.userId = userId;
        this.pin = pin;
        this.accounts = new ArrayList<>();
        // For simplicity, each user has one account
        this.accounts.add(new Account(userId, 0.0));
    }

    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Account getAccount() {
        return accounts.get(0);
    }
}
