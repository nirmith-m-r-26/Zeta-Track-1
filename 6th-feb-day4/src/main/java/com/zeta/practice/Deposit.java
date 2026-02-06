package com.zeta.practice;

import com.zeta.account.BankAccount;

import java.util.Objects;
import java.util.concurrent.Callable;

public class Deposit implements Callable {
    private final BankAccount bankAccount;
    private  final int amount;

    public Deposit(BankAccount bankAccount, int amount) {
        this.bankAccount = bankAccount;
        this.amount = amount;
    }

    @Override
    public Object call() throws Exception{
        Thread.sleep((long) (Math.random() * 1000));
        String thread = Thread.currentThread().getName();
        System.out.println(thread+" depositing ₹"+amount);
        bankAccount.deposit(amount);
        System.out.println(thread+" completed deposit of ₹"+amount);
        return bankAccount.getBalance();
    }
}
