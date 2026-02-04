package com.zeta;

import java.util.logging.Logger;

public class Bank {
    Logger log = Logger.getLogger("bank");
    int id=1;
    public boolean transfer(float amt, Account source, Account dest){
        try{
            source.withdraw(amt, source);
        }catch(IllegalArgumentException e){
            log.severe(e.getMessage());
            log.warning("A TRACE Message:");
            log.info("Fetching customer ");
        }

        dest.deposit(amt, dest);
        return true;
    }
}
