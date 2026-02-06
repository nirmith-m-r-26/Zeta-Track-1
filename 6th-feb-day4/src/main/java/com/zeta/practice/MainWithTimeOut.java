package com.zeta.practice;
import com.zeta.account.BankAccount;

import java.util.concurrent.*;

public class MainWithTimeOut {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        depositMoney();
    }

    private static void depositMoney() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        BankAccount account = new BankAccount(1000);
        Future future = executorService.submit(new Deposit(account , 1000));
//		Future future1 = executorService.submit(new DepositTask(account , 1000));
        try {
            System.out.println(future.get(10, TimeUnit.SECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }//blocking
//		System.out.println(future1.get());
//		Thread.sleep(3000);
        System.out.println(account.getBalance());
    }
}