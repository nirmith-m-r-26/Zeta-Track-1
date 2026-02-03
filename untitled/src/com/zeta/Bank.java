package com.zeta;

public class Bank {
    public boolean transfer(float amt, Account source, Account dest){
        if(amt>source.getBalance()) {
            source.withdraw(amt);
        }else{
            return false;
        }
        dest.deposit(amt);
        return true;
    }
}
