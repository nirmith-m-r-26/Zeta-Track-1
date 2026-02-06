package com.zeta.executor;

import com.zeta.account.BankAccount;
import com.zeta.account.LoanAccount;

public class LoanIssueTask implements Runnable {

    BankAccount bankAccount;
    int loanAmount;
    float interestRate;
    float tenure;

    public LoanIssueTask(BankAccount bankAccount, int loanAmount, float interestRate, float tenure) {
        this.bankAccount = bankAccount;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.tenure = tenure;
    }


    @Override
    public void run() {
        String thread = Thread.currentThread().getName();
        System.out.println(thread+" Issuing Loan ₹"+loanAmount);
        try{
            bankAccount.getLoanAccount().getLoanAmount();
            System.out.println("You already have a loan!!");
            return;
        }catch (Exception e){}
        if(bankAccount.getBalance()>=100000){
            LoanAccount loanAccount = new LoanAccount(loanAmount, interestRate, tenure);
            bankAccount.setLoanAccount(loanAccount);
            System.out.println("Loan issued...");
        }else{
            System.out.println("Balance is less than 1 lakh, so no loan... ");
        }
    }
}
