package com.zeta;

import com.zeta.executor.DepositTask;
import com.zeta.executor.LoanIssueTask;
import com.zeta.executor.WithdrawTask;
import com.zeta.account.BankAccount;
import lambda.Case;
import lambda.Menu;

import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BankingApp {
    static int baseAccountNumber = 1001;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, BankAccount> allAccounts = new HashMap<>();

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Menu menu = () -> {
            System.out.println("\n===== MULTITHREADED BANKING SYSTEM (ExecutorService + Lambda + Collections) ====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Simulate Parallel Withdrawals");
            System.out.println("5. Loan Issue");
            System.out.println("6. Show Loan amount");
            System.out.println("7. Create account"); //TODO: do case 8,9
            System.out.println("8. View Transactions");
            System.out.println("9. Show all accounts");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
        };

        Case case1 = () -> {
            try {
                System.out.println("Enter account number: ");
                int accountNumber = scanner.nextInt();
                Validator.validateAccountNumber(accountNumber);
                System.out.println("Your Account Balance: ₹" + allAccounts.get(accountNumber).getBalance());
            } catch (Exception e) {
                System.out.println("Invalid Account number entered...☹️ OR No account Found 👎");
            }
        };

        Case case2 = () -> {
            try {
                System.out.println("Enter amount to deposit: ");
                int deposit = scanner.nextInt();
                Validator.validateAmount(deposit);
                System.out.println("Enter account number: ");
                int accountNumber = scanner.nextInt();
                Validator.validateAccountNumber(accountNumber);
                BankAccount bankAccount = allAccounts.get(accountNumber);
                bankAccount.deposit(deposit);
            } catch (Exception e) {
                System.out.println("ç");
            }
        };

        Case case3 = () -> {
            try {
                System.out.print("Enter amount to withdraw: ₹");
                int withdraw = scanner.nextInt();
                Validator.validateAmount(withdraw);
                System.out.println("Enter account number: ");
                int accountNumber = scanner.nextInt();
                Validator.validateAccountNumber(accountNumber);
                BankAccount bankAccount = allAccounts.get(accountNumber);
//                executor.execute(new WithdrawTask(bankAccount, withdraw));
                bankAccount.withdraw(withdraw);
            } catch (Exception e) {
                System.out.println("Invalid Account number entered...☹️");
            }
        };

        Case case4 = () -> {
            try {
                System.out.println("Enter account number: ");
                int accountNumber = scanner.nextInt();
                Validator.validateAccountNumber(accountNumber);
                BankAccount bankAccount = allAccounts.get(accountNumber);
                System.out.println("Simulating two parallel withdrawals of ₹" + (bankAccount.getBalance() / 2));

                Future<?> future = executor.submit(new WithdrawTask(bankAccount, bankAccount.getBalance() / 2));
                try {
                    future.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
                executor.submit(new WithdrawTask(bankAccount, bankAccount.getBalance() / 2));
                executor.shutdown();
            } catch (Exception e) {
                System.out.println("Invalid Account number entered...☹️");
            }
        };

        Case case5 = () -> {
            try {
                System.out.println("Issue loan: ");
                System.out.println("Enter account number: ");
                int accountNumber = scanner.nextInt();
                Validator.validateAccountNumber(accountNumber);
                System.out.println("Enter loanAmount, tenure");
                BankAccount bankAccount = allAccounts.get(accountNumber);
                executor.execute(new LoanIssueTask(bankAccount, scanner.nextInt(), 10, scanner.nextFloat()));
            } catch (NullPointerException nullPointerException) {
                System.out.println("Account does not exist... ");
            } catch (Exception e) {
                System.out.println("Invalid Account number entered...☹️");
            }
        };

        Case case6 = () -> {
            try {
                System.out.println("Enter account number: ");
                int accountNumber = scanner.nextInt();
                Validator.validateAccountNumber(accountNumber);
                BankAccount bankAccount = allAccounts.get(accountNumber);
                System.out.println(bankAccount.getLoanAccount().getLoanAmount());
            } catch (Exception e) {
                System.out.println("No loans available 🥳");
            }
        };

        Case case7 = () -> {
            System.out.println("Welcome to Namma Bank 🏦\nYou are about to open a new account in your Namma Bank.\nPlease click Enter to open account");
            scanner.nextLine();
            scanner.nextLine();
            allAccounts.put(baseAccountNumber, new BankAccount(0, baseAccountNumber));
            System.out.println("Your account number is: " + baseAccountNumber);
        };

        Case case8 = () -> {
            try {
                System.out.println("Enter account number: ");
                int accountNumber = scanner.nextInt();
                Validator.validateAccountNumber(accountNumber);
                BankAccount bankAccount = allAccounts.get(accountNumber);
                System.out.println("All Transactions:\n"+bankAccount.getTransactionHistory());
            } catch (Exception e) {
                System.out.println("Invalid Account number entered...☹️");
            }
        };

        Case case9 = ()->{
            System.out.println("All accounts :\n"+allAccounts.keySet());
        };

        Case case10 = () -> {
            System.out.println("Shutting down banking system...");
            scanner.close();
            System.exit(0);
        };

        Case casedef = () -> {
            System.out.println("Invalid choice! Try again.");
        };

        while (true) {
            System.out.println("Click Enter for menu: ");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) break;
            }
            scanner.nextLine();
            menu.print();

            try {
                int choice = scanner.nextInt();

                switch (choice) {

                    case 1:
                        case1.execute();
                        break;

                    case 2:
                        case2.execute();
                        break;

                    case 3:
                        case3.execute();
                        break;

                    case 4:
                        case4.execute();
                        break;

                    case 5:
                        case5.execute();
                        break;

                    case 6:
                        case6.execute();
                        break;
                    case 7:
                        case7.execute();
                        baseAccountNumber++;
                        break;
                    case 8:
                        case8.execute();
                        break;
                    case 9:
                        case9.execute();
                        break;
                    case 10:
                        case10.execute();
                        break;
                    default:
                        casedef.execute();
                }
            } catch (Exception e) {
                scanner.next();
                System.out.println("Invalid Character Entered.... Please enter number 1️⃣-🔟");
            }
        }
    }
}
