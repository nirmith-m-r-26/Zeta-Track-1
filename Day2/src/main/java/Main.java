
public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.issueCard("Nirmith M R");
        bank.issueCard("Zeta");
        System.out.println(bank.cards);
    }
}