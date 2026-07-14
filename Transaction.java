import java.time.LocalDateTime;

public class Transaction {

    private static int nextTransactionId = 100001;

    private int transactionId;
    private String type;
    private double amount;
    private double balanceAfterTransaction;
    private LocalDateTime dateTime;
    private String description;

    public Transaction(String type, double amount, double balanceAfterTransaction, String description) {
        this.transactionId = nextTransactionId++;
        this.type = type;
        this.amount = amount;
        this.balanceAfterTransaction = balanceAfterTransaction;
        this.description = description;
        this.dateTime = LocalDateTime.now();
    }

    public int getTransactionId() {
        return transactionId;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    
    public String getDescription(){
        return description;
    }
}