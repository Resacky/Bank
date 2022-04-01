import java.lang.reflect.Array;
import java.util.ArrayList;

public class Bank {

    private ArrayList<Account> accountList;

    public Bank() {
        accountList = new ArrayList<Account>();
    }

    public void addAccount(Account account) {
        accountList.add(account);
    }

    public Account getAccount(int accountID) {
        for (Account currentAcc : accountList) {
            if (currentAcc.getAccID() == accountID) {
                return currentAcc;
            }
        }
        return null;
    }

    public void deposit(int accID, double amount) {
        Transaction transaction = new Transaction(accID, amount, Transaction.TransactionType.DEPOSIT);

        Account acc = getAccount(accID);
        acc.addTrans(transaction);
        acc.addBalance(amount);
    }

    public void withdrawal(int accID, double amount) {
        Transaction transaction = new Transaction(accID, amount, Transaction.TransactionType.WITHDRAWAL);

        Account acc = getAccount(accID);
        acc.addTrans(transaction);
        acc.removeBalance(amount);
    }

    public double getAccBal(Account Acc) {
        return Acc.getBalance();
    }

    public double getAccBal(int accID) {
        return getAccount(accID).getBalance();
    }

    public ArrayList<Transaction> getAccountTransactions(int accID) {
        return getAccount(accID).getTransactionHistory();
    }

    public boolean accExists(int accID) {
        return getAccount(accID) != null;
    }

    public void accountDatabase() {
        if (accountList.isEmpty()) {
            System.err.println("There are no accounts within our database!");
            return;
        }
        for (Account currentAccount : accountList) {
            System.out.println(currentAccount);
        }
    }

    public void listAccountTransactions(int accID) {
        ArrayList<Transaction> transactionHistory = getAccountTransactions(accID);

        if (transactionHistory.isEmpty()){
            System.err.println("No transactions!");
            return;
        }

        for (Transaction transaction : transactionHistory) {

            switch (transaction.getTransactionType()) {

                case DEPOSIT:
                    System.out.println("Transaction type: Deposit, Amount: $" + transaction.getAmount());
                    break;
                case WITHDRAWAL:
                    System.out.println("Transaction type: Withdrawn, Amount: $" + transaction.getAmount());
                    break;
            }
        }
    }
}
