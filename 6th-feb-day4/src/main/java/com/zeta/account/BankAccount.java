package com.zeta.account;

import com.zeta.transaction.STATUS;
import com.zeta.transaction.Transaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private int accountNumber;
    private int balance;
    private LoanAccount loanAccount;
    private List<Transaction> transactionHistory;

    public BankAccount() {
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void addTransaction(Transaction transaction){
        transactionHistory.add(transaction);
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public LoanAccount getLoanAccount() {
        return loanAccount;
    }

    public void setLoanAccount(LoanAccount loanAccount) {
        this.loanAccount = loanAccount;
    }

    public BankAccount(int balance, int accountNumber) {
        this.balance = balance;
        this.accountNumber=accountNumber;
        this.transactionHistory = new ArrayList<>();
    }

    public synchronized int getBalance() {
        return balance;
    }

    public synchronized boolean withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " checking balance...");

        if (balance >= amount) {
            try { Thread.sleep(500); } catch (InterruptedException e) {}
            balance -= amount;
            transactionHistory.add(new Transaction(STATUS.SUCCESS, LocalDateTime.now(), amount*(-1), null));
            System.out.println("Withdrawed: ₹"+amount+"is success ✅\nCurrent balance: ₹"+getBalance());
            return true;
        }
        transactionHistory.add(new Transaction(STATUS.FAILURE, LocalDateTime.now(), amount*(-1), null));
        System.out.println("Withdraw: ₹"+amount+" Failed 😞❌\nCurrent balance: ₹"+getBalance());
        return false;
    }

    public synchronized void withdraw(int amount, BankAccount bankAccount){
        balance-=amount;
        transactionHistory.add(new Transaction(STATUS.SUCCESS, LocalDateTime.now(), amount*(-1), bankAccount));
    }

    public synchronized void deposit(int amount) {
        balance += amount;
        transactionHistory.add(new Transaction(STATUS.SUCCESS, LocalDateTime.now(), amount, null));
        System.out.println("Deposited: ₹"+amount+" ✅\nCurrent balance: ₹"+getBalance());
    }

    public synchronized void deposit(int amount, BankAccount bankAccount){
        balance+=amount;
        transactionHistory.add(new Transaction(STATUS.SUCCESS, LocalDateTime.now(), amount, bankAccount));
    }
}