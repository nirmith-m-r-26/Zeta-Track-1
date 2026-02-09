package forkjoin;
import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

class SumTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 3;
    private final ArrayList<Account> accounts;
    private final int start;
    private final int end;

    SumTask(ArrayList<Account> accounts, int start, int end) {
        this.accounts = accounts;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start <= THRESHOLD) {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += accounts.get(i).getBalance();
            }
            return sum;
        } else {
            int mid = (start + end) / 2;
            SumTask left = new SumTask(accounts, start, mid);
            SumTask right = new SumTask(accounts, mid, end);

            right.fork();          // start async
            int leftResult = left.compute();
            int rightResult = right.join();

            return leftResult + rightResult;
        }
    }
}

public class ForkJoinDemo {
    public static void main(String[] args) {
        ArrayList<Account> accounts = new ArrayList<Account>();

        System.out.println("Initial Balance: ");
        for (int i = 0; i < 100; i++) {
            accounts.add(new Account( ((int) (Math.random() * 1000))));
            System.out.print(accounts.get(accounts.size()-1).getBalance()+"  ");
        }
        System.out.println("\n\n");

        ForkJoinPool pool = new ForkJoinPool();
        int result = pool.invoke(new SumTask(accounts, 0, accounts.size()));

        System.out.println("Sum = " + result);
    }
}