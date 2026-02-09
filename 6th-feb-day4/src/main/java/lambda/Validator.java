package lambda;

import java.util.Scanner;

@FunctionalInterface
public interface Validator {
    int validate(int amount1, Scanner scanner1);
}
