package com.zeta;

public class SavingAccount extends Account{

    public SavingAccount(int i) {
        super(i);
    }

    @Override
    public float deposit(float amount, Account acc) {
        acc.setBalance(acc.getBalance()+amount);
        return amount;
    }

    @Override
    public float withdraw(float amount, Account acc) {
        if(amount <= acc.getBalance()){
            acc.setBalance(acc.getBalance()-amount);
        }else{
            throw new IllegalArgumentException("amount is more than balance");
        }
        System.out.println("Withdraw: "+getBalance());
        return acc.getBalance();
    }
}
