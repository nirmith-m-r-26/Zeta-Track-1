package com.zeta.account;

public class LoanAccount {
    private int loanAmount;
    private float interestRate;
    private float tenure;

    public LoanAccount(int loanAmount, float interestRate, float tenure) {
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.tenure = tenure;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public float getTenure() {
        return tenure;
    }
}
