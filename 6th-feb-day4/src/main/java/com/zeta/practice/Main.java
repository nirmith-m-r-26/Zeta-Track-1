package com.zeta.practice;

import com.zeta.account.BankAccount;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        BankAccount bankAccount = new BankAccount(1, 1000);
        Future future = executorService.submit(new Deposit(bankAccount, 1000));
        Future future1 = executorService.submit(new Deposit(bankAccount, 2000));
        System.out.println(future.get());
        System.out.println(future1.get());
        System.out.println(bankAccount.getBalance());
        executorService.shutdown();
    }
}
