import java.util.ArrayList;
import java.time.LocalDate;

public class Bank {
    private final ArrayList<Account> accounts;
    private  int nextAccountNumber = 100001;

    public Bank() {                                   //Constructor
        accounts = new ArrayList<>();
    }

    private  Account findAccount(int accountNumber) {                   //Method to find an account by account number
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null; // Account not found
    }

    public void createAccount(String name, LocalDate dob, String mobileNumber, String email, int pin, double initialDeposit) {                       //Method to add an account to the bank
        Account account = new Account(nextAccountNumber++, name, dob, mobileNumber, email, pin, initialDeposit);
        accounts.add(account);
        System.out.println("====================================");
        System.out.println("Account Created Successfully.");
        System.out.println("Account Number : " + account.getAccountNumber());
        System.out.println("Name           : " + name);
        System.out.println("====================================");
    }

    public Account login(int accountNumber, int pin) {                       //Method to login to an account
        Account account = findAccount(accountNumber);
        if(account == null){
            System.out.println("Account not found.");
            return null;
        }
        if(!account.isActive()){
            System.out.println("Account is deactivated. Cannot login.");
            return null;
        }
        if(!account.verifyPin(pin)){
            System.out.println("Incorrect PIN.");
            return null;
        }
        System.out.println("Login successful.");
        return account;
    }

    public void deposit(int accountNumber, double amount) {                       //Method to deposit money into an account
        Account account = findAccount(accountNumber);
        if(account == null){
            System.out.println("Account not found.");
            return;
        }
        if(!account.isActive()){
            System.out.println("Account is deactivated. Cannot deposit.");
            return;
        }
        account.deposit(amount, "Cash Deposit");
    }

    public void transferMoney(Account fromAccount, int toAccountNumber, double amount) {                       //Method to transfer money from one account to another
        Account toAccount = findAccount(toAccountNumber);
        if(toAccount == null){
            System.out.println("Destination account not found.");
            return;
        }
        if(!toAccount.isActive()){
            System.out.println("Destination account is deactivated. Cannot transfer.");
            return;
        }
        if(fromAccount.getAccountNumber() == toAccountNumber){
            System.out.println("Cannot transfer to the same account.");
            return;
      }
        if(amount <= 0){
            System.out.println("Invalid transfer amount.");
            return;
        }
        if(fromAccount.getBalance() < amount){
            System.out.println("Insufficient balance in source account.");
            return; 
        }
        fromAccount.withdraw(amount, "Transfer to Account " + toAccountNumber);
        toAccount.deposit(amount, "Transfer from Account " + fromAccount.getAccountNumber());
        System.out.println("₹" + amount + " transferred successfully.");
    }


    public void displayAllAccounts() {                       //Method to display all the accounts in the bank
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        System.out.println("--------------------------------------------------------------------");
        System.out.printf("%-12s %-20s %-15s %-10s%n",
        "Acc No",
        "Name",
        "Balance",
        "Status");
        System.out.println("--------------------------------------------------------------------");

        for(Account account : accounts){

            System.out.printf("%-12d %-20s ₹%-14.2f %-10s%n",
            account.getAccountNumber(),
            account.getName(),
            account.getBalance(),
            account.isActive() ? "Active" : "Inactive");
     }

        System.out.println("--------------------------------------------------------------------");
    }

    public int totalAccounts() {                       //Method to get the total number of accounts in the bank
        return accounts.size();
    }
    
    public void bankSummary() {                       //Method to display the summary of the bank
        System.out.println("================ BANK SUMMARY ================");
        System.out.println("Total Accounts: " + totalAccounts());
        int activeAccounts = 0;
        int inactiveAccounts = 0;
        double totalBalance = 0.0;
        double averageBalance = 0.0;
        double highestBalance = Double.MIN_VALUE;
        double lowestBalance = Double.MAX_VALUE;
        for(Account account : accounts) {
            if (account.isActive()) {
                activeAccounts++;
            } else {
                inactiveAccounts++;
            }
            totalBalance += account.getBalance();
            highestBalance = Math.max(highestBalance, account.getBalance());
            lowestBalance = Math.min(lowestBalance, account.getBalance());
        }
        if (totalAccounts() > 0) {
            averageBalance = totalBalance / totalAccounts();
        }
        System.out.println("Active Accounts: " + activeAccounts);
        System.out.println("Deactivated Accounts: " + inactiveAccounts);
        System.out.println("Total Balance: ₹" + totalBalance);
        System.out.println("Average Balance: ₹" + averageBalance);
        System.out.println("Highest Balance: ₹" + highestBalance);
        System.out.println("Lowest Balance: ₹" + lowestBalance);
        System.out.println("==============================================");
    }
}
