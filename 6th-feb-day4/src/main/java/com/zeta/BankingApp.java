package com.zeta;

import com.zeta.executor.DepositTask;
import com.zeta.executor.WithdrawTask;
import com.zeta.thread.BankAccount;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BankingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter initial bank balance");
        int initialBalance = scanner.nextInt();

        initialBalance = Validator.validateAmount(initialBalance, scanner);

        BankAccount bankAccount = new BankAccount(initialBalance);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        while(true){
            System.out.println("\n===== MULTITHREADED BANKING SYSTEM (ExecutorService ====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Simulate Parallel Withdrawals");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Current Balance: ₹" + bankAccount.getBalance());
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ₹");
                    int dep = scanner.nextInt();
                    dep = Validator.validateAmount(dep, scanner);
                    executor.execute(new DepositTask(bankAccount, dep));
                    break;

                case 3:
                    System.out.print("Enter amount to withdraw: ₹");
                    int w = scanner.nextInt();
                    w = Validator.validateAmount(w,scanner);
                    executor.execute(new WithdrawTask(bankAccount, w));
                    break;

                case 4:
                    System.out.println("Simulating two parallel withdrawals of ₹" + (initialBalance / 2));

                    Future<?> future = executor.submit(new WithdrawTask(bankAccount, bankAccount.getBalance() / 2));
                    try {
                        future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                    executor.submit(new WithdrawTask(bankAccount, bankAccount.getBalance() / 2));
                    executor.shutdown();
                    break;

                case 5:
                    System.out.println("Shutting down banking system...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        }
    }

}
