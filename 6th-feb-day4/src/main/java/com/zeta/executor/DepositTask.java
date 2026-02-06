package com.zeta.executor;

import com.zeta.thread.BankAccount;

public class DepositTask implements Runnable{
    private final BankAccount bankAccount;
    private  final int amount;

    public DepositTask(BankAccount bankAccount, int amount) {
        this.bankAccount = bankAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
        String thread = Thread.currentThread().getName();
        System.out.println(thread+" depositing ₹"+amount);
        bankAccount.deposit(amount);
        System.out.println(thread+" completed deposit of ₹"+amount);
    }
}
