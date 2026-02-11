package com.zeta;

import java.util.Scanner;

public class Validator {
    public static int validateInitialAmount(int amount, Scanner scanner) {
        lambda.Validator validator = (amount1, scanner1)->{
            if (amount1 < 0) {
                while (true) {
                    System.out.println("Initial balance cannot be -ve");
                    System.out.println("-----------------------------");
                    System.out.println("Enter initial balance");
                    amount1 = scanner.nextInt();
                    if (amount1 >= 0) break;
                }
            }
            return amount1;
        };
        return validator.validate(amount, scanner);
    }

    public static boolean validateAccountNumber(int accountNumber) throws Exception {
        if(accountNumber >1000){
            return true;
        }
        throw new Exception("Invalid Account number");
    }

    public static boolean validateAmount(int amount) throws Exception {
        if(amount<=0){
            throw new Exception("Invalid amount entered");
        }
        return true;
    }
}
