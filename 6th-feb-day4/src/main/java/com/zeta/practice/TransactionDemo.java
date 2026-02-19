package com.zeta.practice;

import com.zeta.account.BankAccount;
import com.zeta.transaction.Transaction;

import java.util.Objects;

public class TransactionDemo {
    public static void main(String[] args) {
        BankAccount bankAccount1 = new BankAccount();
        BankAccount bankAccount2 = new BankAccount();

        bankAccount1.deposit(100000);
        bankAccount2.deposit(100000);

        try {
            Transaction.transact(1000, bankAccount1, bankAccount2);
            Transaction.transact(10000, bankAccount1, bankAccount2);
            Transaction.transact(1000, bankAccount2, bankAccount1);
        } catch (Exception e) {
            System.out.println("Insufficient Balance");
        }

        System.out.println("Transaction History of BankAccount1: \n"+bankAccount1.getTransactionHistory());
        System.out.println("Transaction History of BankAccount2: \n"+bankAccount2.getTransactionHistory());

    }
}
