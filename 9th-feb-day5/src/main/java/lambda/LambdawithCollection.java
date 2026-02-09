package lambda;

import forkjoin.Account;

import java.util.ArrayList;
import java.util.List;

public class LambdawithCollection {
    public static void main(String[] args) {
        showWithArrayList();
    }

    private static void showWithArrayList() {
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            accounts.add(new Account(i+1));
        }
        accounts.forEach(account -> System.out.println(account));
        System.out.println("after sorting -----------------");
        accounts.sort((account1, account2) -> account2.getBalance()-account1.getBalance());
        accounts.forEach(account -> System.out.println(account.getBalance()));

    }
}
