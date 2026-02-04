package com.zeta;

public class CurrentAccount extends Account{
    public CurrentAccount(int number) {
        super(number);
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
