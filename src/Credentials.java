public class Credentials {
    private String accountName;
    private int accountID;

    public Credentials(String accountName, int accountID) {
        this.accountName = accountName;
        this.accountID = accountID;
    }

    public String getAccountName() {
        return accountName;
    }

    public int getAccountID() {
        return accountID;
    }
}
