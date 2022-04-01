import java.util.ArrayList;

public class Account {

    private Credentials accountCredentials;
    private ArrayList<Transaction> transactionHistory;
    private double balance;
    private Privileges accType;

    public static enum Privileges {
        ADMIN, CLIENT
    }

    public Account(Credentials accountCredentials) {
        this(accountCredentials, 0, Privileges.CLIENT);
    }

    public Account(Credentials accountCredentials, double balance) {
        this(accountCredentials, balance, Privileges.CLIENT);
    }

    public Account(Credentials accountCredentials, Privileges accType) {
        this(accountCredentials, 0, accType);
    }

    public Account(Credentials accountCredentials, double balance, Privileges accType){

        this.accountCredentials = accountCredentials;
        this.balance = balance;
        this.accType = accType;
        transactionHistory = new ArrayList<Transaction>();

    }

    public void addTrans(Transaction transaction){
        transactionHistory.add(transaction);
    }

    public void addBalance(double amount) {
        balance += amount;
    }

    public void removeBalance(double amount) {
        balance -= amount;
    }

    public Credentials getAccountCredentials() {
        return accountCredentials;
    }

    public int getAccID() {
        return accountCredentials.getAccountID();
    }

    public String getAccName(){
        return accountCredentials.getAccountName();
    }

    public double getBalance() {
        return balance;
    }

    public Privileges getAccType() {
        return accType;
    }

    public ArrayList<Transaction> getTransactionHistory(){
        return transactionHistory;
    }

    @Override
    public String toString() {
        return "Account Name: " + getAccName() + ", Account ID: " +  getAccID();
    }
}
