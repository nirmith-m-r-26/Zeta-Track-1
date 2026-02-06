package com.zeta.executor;

import com.zeta.account.BankAccount;

public class WithdrawTask implements Runnable{
    private final BankAccount bankAccount;
    private final int amount;


    public WithdrawTask(BankAccount bankAccount, int amount) {
        this.bankAccount = bankAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
        String thread = Thread.currentThread().getName();
        System.out.println(thread+" attempting to winthdraw ₹"+amount);

        boolean success = bankAccount.withdraw(amount);
        if(success){
            System.out.println(thread+" successfully withdrew ₹"+ amount);
        }else {
            System.out.println(thread+" failed to withdraw ₹"+amount+" (Insufficient balance");
        }
    }
}
