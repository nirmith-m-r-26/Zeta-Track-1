import com.zeta.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Account account=new SavingAccount(123);
//        account.number = 345;
        Bank bank = new Bank();
        System.out.println(account.getNumber());
//        account.type = ACCOUNT_TYPE.SAVINGS;
        Account current = new CurrentAccount(999);
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        accounts.add(current);
        accounts.forEach(account1 -> {
            account1.deposit(2000, account1);
            account1.withdraw(1000, account1);
            System.out.println(account1.getBalance());
        });
        System.out.println(bank.transfer(10000, account, current));
        System.out.println(account.getBalance());
        System.out.println(current.getBalance());

    }
}
