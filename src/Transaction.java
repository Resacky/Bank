public class Transaction {

    //enum is a 'constant' variable list
    public static enum TransactionType {
        DEPOSIT, WITHDRAWAL

        //DEPOSIT has the value of 0
        //WITHDRAWAL has the value of 1
        //it follows the value of a list 2...3...etc.
    }

    private int accID;
    private double amount;
    private TransactionType transactionType;

    public Transaction(int accID, double amount, TransactionType transactionType) {
        this.accID = accID;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public int getAccID() {
        return accID;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }
}
