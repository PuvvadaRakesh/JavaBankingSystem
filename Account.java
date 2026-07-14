import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Account {

    private final int accountNumber;
    private String name;
    private LocalDate dob;
    private String mobileNumber;
    private String email;
    private int pin;
    private double balance;
    private ArrayList<Transaction> transactionHistory = new ArrayList<>();
    private final LocalDate accountCreatedDate;
    private boolean active = true;

    Account(int accountNumber, String name, LocalDate dob, String mobileNumber, 
        String email, int pin, double initialDeposit){
        if (initialDeposit < 0) {
            throw new IllegalArgumentException("Initial deposit cannot be negative.");
        }
        
        this.accountNumber = accountNumber;
        this.name = name;
        this.dob = dob;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.pin = pin;
        this.balance = initialDeposit;
        this.accountCreatedDate = LocalDate.now();
        transactionHistory.add(new Transaction(
                        "Deposit",
                        initialDeposit,
                        balance,
                        "Account Opening Deposit"
                        )
                    );
        System.out.println("₹" + initialDeposit + " deposited successfully.");
    }

    // Constructor will initialize accountNumber and accountCreatedDate

    // -------------------- Getters --------------------

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public int getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDate getAccountCreatedDate() {
        return accountCreatedDate;
    }

    // public ArrayList<Transaction> getTransactionHistory() {
    //     return transactionHistory;
    // }

    // -------------------- Setters --------------------

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void deposit(double amount){                                 //deposit method to add money to the account
        if(amount <= 0){ 
            System.out.println("Invalid deposit amount");
            return;
        }
        this.balance += amount;
        Transaction transaction = new Transaction("Deposit", amount, balance, "Cash Deposit");
        this.transactionHistory.add(transaction);
        System.out.println("₹" + amount + " deposited successfully.");
    }

    public void withdraw(double amount) {                                 //withdraw method to withdraw money from the account

    if (amount <= 0) {
        System.out.println("Invalid withdrawal amount.");
        return;
    }

    if (amount > balance) {
        System.out.println("Insufficient balance.");
        return;
    }

    balance -= amount;

    transactionHistory.add(
        new Transaction("Withdraw", amount, balance, "Cash Withdrawal")
    );

    System.out.println("₹" + amount + " withdrawn successfully.");
    }
    
    public void changePin(int oldPin, int newPin) {                       //changePin method to change the pin of the account

    if(!verifyPin(oldPin)){
        System.out.println("Incorrect old PIN.");
        return;
    }
    if (oldPin == newPin) {
    System.out.println("New PIN cannot be same as old PIN.");
    return;
    }

    if (newPin < 1000 || newPin > 9999) {
    System.out.println("PIN must be a 4-digit number.");
    return;
    }

        this.pin = newPin;

        transactionHistory.add(
        new Transaction(
        "Security",
        0,
        balance,
        "PIN Changed"
        )
    );

System.out.println("PIN updated successfully.");
    }
    
    public boolean verifyPin(int pin){                                          //verifyPin method to verify the pin of the account
        if(pin < 1000 || pin > 9999){
            System.out.println("PIN must be a 4-digit number.");
            return false;
        }
        return this.pin == pin;
    }

    public void updateProfile(String name, String mobile, String email){           //updateProfile method to update the profile of the account
        this.name = name;
        this.mobileNumber = mobile;
        if(email.contains("@")){
            this.email = email;
        }else{
            System.out.println("Invalid Email.");
        }
    }

    public void deactivateAccount() {                         //deactivateAccount method to deactivate the account
    active = false;

    transactionHistory.add(
        new Transaction(
            "Account",
            0,
            balance,
            "Account Deactivated"
        )
    );
    }     

    

    public void displayTransactionHistory() {                    //displayTransactionHistory method to display the transaction history of the account

    if (transactionHistory.isEmpty()) {
        System.out.println("\nNo Transactions Found.");
        return;
    }

    DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

   System.out.println("--------------------------------------------------------------------------------------------------------------------");
    System.out.printf("%-10s %-20s %-12s %-12s %-12s %-30s%n",
        "Txn ID",
        "Date & Time",
        "Type",
        "Amount",
        "Balance",
        "Description");
    System.out.println("--------------------------------------------------------------------------------------------------------------------");

    for (Transaction t : transactionHistory) {

        System.out.printf("%-10d %-20s %-12s ₹%-11.2f ₹%-11.2f %-30s%n",
            t.getTransactionId(),
            t.getDateTime().format(formatter),
            t.getType(),
            t.getAmount(),
            t.getBalanceAfterTransaction(),
            t.getDescription());
    }

    System.out.println("--------------------------------------------------------------------------------------------------------------------");
}

public void displayProfile() {                                              //displayProfile method to display the profile of the account
    System.out.println("\n========== ACCOUNT DETAILS ==========");
    System.out.println("Account Number : " + accountNumber);
    System.out.println("Name           : " + name);
    System.out.println("Date of Birth  : " + dob);
    System.out.println("Age            : " + getAge());
    System.out.println("Mobile Number  : " + mobileNumber);
    System.out.println("Email          : " + email);
    System.out.println("Balance        : ₹" + balance);
    System.out.println("Account Created: " + accountCreatedDate);
    System.out.println("Status         : " + (active ? "Active" : "Inactive"));
    System.out.println("=====================================\n");
}

@Override
public String toString() {
    return "Account Number : " + accountNumber +
           "\nName           : " + name +
           "\nBalance        : ₹" + balance;
}

}