package com.zeta;

import java.util.Scanner;

public class Validator {
    public static int validateAmount(int amount, Scanner scanner) {
        if (amount < 0) {
            while (true) {
                System.out.println("Initial balance cannot be -ve");
                System.out.println("-----------------------------");
                System.out.println("Enter initial balance");
                amount = scanner.nextInt();
                if (amount >= 0) break;
            }
        }
        return amount;
    }
}
