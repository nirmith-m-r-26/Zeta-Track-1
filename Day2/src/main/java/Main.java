import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(String.valueOf(Main.class));
        Bank bank = new Bank();
        bank.issueEmeraldCard("Nirmith M R");
        bank.issueEmeraldCard("Zeta");
        bank.issueInfeniaCard("Nirmith M R");
        bank.issueInfeniaCard("Nitin");
        logger.info(bank.cards.toString());
    }
}
