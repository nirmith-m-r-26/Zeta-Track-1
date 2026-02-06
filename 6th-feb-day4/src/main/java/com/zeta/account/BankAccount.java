package com.zeta.account;

public class BankAccount {
    private int balance;
    private LoanAccount loanAccount;


    public LoanAccount getLoanAccount() {
        return loanAccount;
    }

    public void setLoanAccount(LoanAccount loanAccount) {
        this.loanAccount = loanAccount;
    }

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public synchronized int getBalance() {
        return balance;
    }

    public synchronized boolean withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " checking balance...");

        if (balance >= amount) {
            try { Thread.sleep(500); } catch (InterruptedException e) {}
            balance -= amount;
            return true;
        }
        return false;
    }

    public synchronized void deposit(int amount) {
        try { Thread.sleep(300); } catch (InterruptedException e) {}
        balance += amount;
    }
}