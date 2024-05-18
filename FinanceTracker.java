import java.util.ArrayList;
import java.util.Scanner;

class Transaction {
    String type;
    double amount;

    Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }
}

public class FinanceTracker {
    private ArrayList<Transaction> transactions;
    private double balance;

    public FinanceTracker() {
        transactions = new ArrayList<>();
        balance = 0;
    }

    public void addTransaction(String type, double amount) {
        transactions.add(new Transaction(type, amount));
        if (type.equalsIgnoreCase("income")) {
            balance += amount;
        } else if (type.equalsIgnoreCase("expense")) {
            balance -= amount;
        }
    }

    public void showTransactions() {
        System.out.println("Transactions:");
        for (Transaction t : transactions) {
            System.out.println(t.type + ": $" + t.amount);
        }
        System.out.println("Current Balance: $" + balance);
    }

    public static void main(String[] args) {
        FinanceTracker tracker = new FinanceTracker();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter transaction type (income/expense) or 'quit' to exit:");
            String type = scanner.nextLine();
            if (type.equalsIgnoreCase("quit")) {
                break;
            }

            System.out.println("Enter amount:");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline

            tracker.addTransaction(type, amount);
        }


        
        tracker.showTransactions();
        scanner.close();
    }
}
