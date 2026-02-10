package com.zeta.transaction;

import com.zeta.account.BankAccount;

import java.time.LocalDateTime;

public class Transaction {
    STATUS status;
    final LocalDateTime dateTime;
    final double amount;
    final BankAccount otherAccount;

    public Transaction(STATUS status, LocalDateTime dateTime, double amount, BankAccount otherAccount) {
        this.status = status;
        this.dateTime = dateTime;
        this.amount = amount;
        this.otherAccount = otherAccount;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public double getAmount() {
        return amount;
    }

    public BankAccount getOtherAccount() {
        return otherAccount;
    }

    public static STATUS transact(int amount, BankAccount source, BankAccount destination) throws Exception {
        if(amount>destination.getBalance()){
            throw new Exception("Insufficient Balance");
        }

        destination.withdraw(amount, source);
        source.deposit(amount, destination);

        return STATUS.SUCCESS;
    }

    @Override
    public String toString() {
        return "Transaction:\nAmount: ₹"+amount+"\nStatus: "+status+"\nDateTime: "+dateTime+"\nOther Bank: "+otherAccount+"\n---------------------\n";
    }
}
