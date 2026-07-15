import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();
        while(true){
            System.out.println("==========================================");
            System.out.println("        BANK MANAGEMENT SYSTEM");
            System.out.println("==========================================");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Login");
            System.out.println("4.  Display All Accounts");
            System.out.println("5.  Display Bank Statistics");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch(choice){
                case 1 :
                    System.out.print("Enter Name: ");
                    sc.nextLine(); // Consume newline
                    String name = sc.nextLine();
                    System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
                    String dobInput = sc.nextLine();
                    LocalDate dob = LocalDate.parse(dobInput);
                    System.out.print("Enter Mobile Number: ");
                    String mobileNumber = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter PIN: ");
                    int pin = sc.nextInt();
                    System.out.print("Enter Initial Deposit: ");
                    double initialDeposit = sc.nextDouble();
                    bank.createAccount(name, dob, mobileNumber, email, pin, initialDeposit);
                    break;
                case 2 :
                    System.out.print("Enter Account Number: ");
                    int accNumber = sc.nextInt();
                    System.out.print("Enter amount to deposit: ");
                    double Amount = sc.nextDouble();
                    bank.deposit(accNumber, Amount);
                    break;
                case 3 :
                    System.out.print("Enter Account Number: ");
                    int accountNumber = sc.nextInt();
                    System.out.print("Enter PIN: ");
                    int enteredPin = sc.nextInt();
                    Account account = bank.login(accountNumber, enteredPin);
                    while(account != null){
                        System.out.println("==========================================");
                        System.out.println("1. Deposit");
                        System.out.println("2. Withdraw");
                        System.out.println("3. Transfer Money");
                        System.out.println("4. View Transaction History");
                        System.out.println("5. Deactivate Account");
                        System.out.println("6. View Profile");
                        System.out.println("7. Update Profile");
                        System.out.println("8. Change PIN");
                        System.out.println("9. check Balance");
                        System.out.println("10. Logout");
                        System.out.print("Enter your choice: ");
                        int subChoice = sc.nextInt();
                        switch(subChoice){
                            case 1 :
                                System.out.print("Enter amount to deposit: ");
                                double depositAmount = sc.nextDouble();
                                account.deposit(depositAmount, "Cash Deposit");
                                break;
                            case 2 :
                                System.out.print("Enter amount to withdraw: ");
                                double withdrawAmount = sc.nextDouble();
                                account.withdraw(withdrawAmount, "Cash Withdrawal");
                                break;
                            case 3 :
                                System.out.print("Enter destination account number: ");
                                int toAccountNumber = sc.nextInt();
                                System.out.print("Enter amount to transfer: ");
                                double transferAmount = sc.nextDouble();
                                bank.transferMoney(account, toAccountNumber, transferAmount);
                                break;
                            case 4 :
                                account.displayTransactionHistory();
                                break;
                            case 5 :
                                account.deactivateAccount();
                                account = null; // Logout after deactivation

                                System.out.println("Account deactivated successfully.");
                                System.out.println("Logged out.");

                                break;
                            case 6 :
                                account.displayProfile();
                                break;
                            case 7 :
                                System.out.print("Enter new name: ");
                                sc.nextLine(); // Consume newline
                                String newName = sc.nextLine();
                                System.out.print("Enter new MObile Number: ");
                                String newMobileNumber = sc.nextLine();
                                System.out.print("Enter new Email: ");
                                String newEmail = sc.nextLine();
                                account.updateProfile(newName, newMobileNumber, newEmail);
                                break;
                            case 8 :
                                System.out.print("Enter old PIN: ");
                                int oldPin = sc.nextInt();
                                System.out.print("Enter new PIN: ");
                                int newPin = sc.nextInt();
                                account.changePin(oldPin, newPin);
                                break;
                            case 9:
                                System.out.println(" Current balance : " + account.getBalance());
                                break;
                            case 10 :
                                account = null; // Logout
                                System.out.println("Logged out successfully.");
                                break;
                            default:
                                System.out.println("Invalid choice.");
                        }
                    }break;
               case 4 :
                    bank.displayAllAccounts();
                    break;
                case 5 :
                    bank.bankSummary();
                    break;
                case 6 :
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}