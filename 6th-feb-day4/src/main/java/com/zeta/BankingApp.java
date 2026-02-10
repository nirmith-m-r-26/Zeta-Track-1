package com.zeta;

import com.zeta.executor.DepositTask;
import com.zeta.executor.LoanIssueTask;
import com.zeta.executor.WithdrawTask;
import com.zeta.account.BankAccount;
import lambda.Case;
import lambda.Menu;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BankingApp {
    private static String s;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter initial bank balance");
        int initialBalance = scanner.nextInt();

        initialBalance = Validator.validateInitialAmount(initialBalance, scanner);

        BankAccount bankAccount = new BankAccount(initialBalance);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Menu menu = () -> {
            System.out.println("\n===== MULTITHREADED BANKING SYSTEM (ExecutorService ====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Simulate Parallel Withdrawals");
            System.out.println("5. Loan Issue");
            System.out.println("6. Show Loan amount");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
        };

        Case case1 = () -> {
            System.out.println("Current Balance: ₹" + bankAccount.getBalance());
        };

        Case case2 = () -> {
            System.out.print(s);
            int dep = scanner.nextInt();
            dep = Validator.validateInitialAmount(dep, scanner);
            executor.execute(new DepositTask(bankAccount, dep));
        };

        Case case3 = () -> {
            System.out.print("Enter amount to withdraw: ₹");
            int w = scanner.nextInt();
            w = Validator.validateInitialAmount(w, scanner);
            executor.execute(new WithdrawTask(bankAccount, w));
        };

        Case case4 = () -> {
            System.out.println("Simulating two parallel withdrawals of ₹" + (bankAccount.getBalance() / 2));

            Future<?> future = executor.submit(new WithdrawTask(bankAccount, bankAccount.getBalance() / 2));
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            executor.submit(new WithdrawTask(bankAccount, bankAccount.getBalance() / 2));
            executor.shutdown();
        };

        Case case5 = () -> {
            System.out.println("Issue loan: ");
            System.out.println("Enter loanAmount, tenure");
            executor.execute(new LoanIssueTask(bankAccount, scanner.nextInt(), 10, scanner.nextFloat()));
        };

        Case case6 = () -> {
            try {
                System.out.println(bankAccount.getLoanAccount().getLoanAmount());
            } catch (Exception e) {
                System.out.println("No loans available");
            }
        };

        Case case7 = () -> {
            System.out.println("Shutting down banking system...");
            scanner.close();
            System.exit(0);
        };

        Case casedef = () -> {
            System.out.println("Invalid choice! Try again.");
        };

        while (true) { // strategy design pattern
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
                        break;


                    default:
                        casedef.execute();
                }
            } catch (Exception e) {
                scanner.next();
                e.printStackTrace();
            }

        }
    }

}
