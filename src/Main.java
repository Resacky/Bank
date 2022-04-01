import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Scanner input;
    static Bank Bitcoin;

    public static void main(String[] args) {

        input = new Scanner(System.in);

        Bitcoin = new Bank();

        Credentials adminCredentials = new Credentials("Admin", 99999);
        Account admin = new Account(adminCredentials, Account.Privileges.ADMIN);
        Bitcoin.addAccount(admin);
        menu();

    }

    public static void menu() {

        String menuInput;
        int accID;

        menuPoint:
        do {

            System.out.println("what would you like to perform? Enter the corresponding number");
            System.out.println("0) Create Account");
            System.out.println("1) login");
            System.out.println("type 'quit' to exit");

            menuInput = input.nextLine().toUpperCase();

            switch (menuInput) {

                case "0":

                    createAccount();
                    break;

                case "1":

                    accIDCheck:
                    while (true) {

                        System.out.println("Enter the account ID: ");
                        accID = input.nextInt();
                        input.nextLine();

                        if (!Bitcoin.accExists(accID)) {
                            System.err.println("Error. Account does not exist!");
                            continue menuPoint;
                        } else {
                            break accIDCheck;
                        }
                    }

                    run(accID);
                    break;

                default:
                    if (!menuInput.equals("QUIT")) {
                        System.err.println("error found. Please type valid input");
                    }
            }
        } while (!menuInput.equals("QUIT"));
    }

    public static void run(int accID) {

        String accName = Bitcoin.getAccount(accID).getAccName();

        System.out.println("\nWelcome! " + accName + " what would you like to do today?");
        if (Bitcoin.getAccount(accID).getAccType() == Account.Privileges.ADMIN) {
            adminView(accName, accID);
        } else {
            clientView(accName, accID);
        }
    }

    public static void createAccount() {

        String accName;

        System.out.println("Enter account name: ");
        accName = input.nextLine();

        int ranAccID = genAccID();

        Account newAccount = new Account(new Credentials(accName, ranAccID));
        Bitcoin.addAccount(newAccount);

        System.out.println("Account " + accName + " created");
        System.out.println("this is your account ID: " + newAccount.getAccID() + "\n");

    }

    public static void deposit(int accID) {

        double amount;

        System.out.println("Enter the amount to deposit: ");
        amount = input.nextDouble();
        input.nextLine();

        Bitcoin.deposit(accID, amount);
        System.out.println();

    }

    public static void Withdrawal(int accID) {

        double amount;

        System.out.println("Enter the amount to withdrawal: ");
        amount = input.nextDouble();
        input.nextLine();

        Bitcoin.withdrawal(accID, amount);
        System.out.println();

    }

    public static void getBalance(int accID) {

        System.out.println("$" + Bitcoin.getAccBal(accID));
        System.out.println();

    }

    public static void accountList() {
        Bitcoin.accountDatabase();
        System.out.println();
    }

    public static void transactionHistory(int accID) {

        Bitcoin.listAccountTransactions(accID);
        System.out.println();

    }

    public static void clientView(String accName, int accID) {

        String inputHold;

        while (true) {

            System.out.println("0) Deposit");
            System.out.println("1) Withdrawal");
            System.out.println("2) Get balance");
            System.out.println("3) Transaction history");
            System.out.println("4) Sign out");

            inputHold = input.nextLine();

            switch (inputHold) {

                case "0":
                    deposit(accID);
                    break;
                case "1":
                    Withdrawal(accID);
                    break;
                case "2":
                    getBalance(accID);
                    break;
                case "3":
                    transactionHistory(accID);
                    break;
                case "4":
                    System.out.println("Goodbye " + accName + " have a good day!\n");
                    return;
                default:
                    if (!inputHold.equals("-1")) {
                        System.err.println("error found. Please type valid input");
                    }
            }
        }
    }

    public static void adminView(String accName, int accID) {

        String inputHold;

        while (true) {

            System.out.println("0) Account list");
            System.out.println("1) Sign out");

            inputHold = input.nextLine();

            switch (inputHold) {

                case "0":
                    accountList();
                    break;
                case "1":
                    return;
                default:
                    if (!inputHold.equals("-1")) {
                        System.err.println("error found. Please type valid input");
                    }
            }
        }
    }

    public static int genAccID() {
        Random randomAccID = new Random(System.nanoTime());
        int accID;

        accID = randomAccID.nextInt(10000, 99999);

        while (Bitcoin.accExists(accID)) {

            accID = randomAccID.nextInt(10000, 99999);

        }

        return accID;
    }
}
