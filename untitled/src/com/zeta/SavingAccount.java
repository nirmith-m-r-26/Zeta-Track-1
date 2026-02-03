package com.zeta;

public class SavingAccount extends Account{

    public SavingAccount(int i) {
        super(i);
    }

    @Override
    public float deposit(float amount) {
        return 0;
    }

    @Override
    public float withdraw(float amount) {
        return amount;
    }
}
