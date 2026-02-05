import java.util.ArrayList;
import java.util.List;

public class Bank {
    List<CreditCardBase> cards = new ArrayList<>();
    CreditCardBase issueEmeraldCard(String customerName) {
        //check for blank
        CreditCardBase card = new EmeraldCreditCard(customerName);
        cards.add(card);
        return card;
    }
    CreditCardBase issueInfeniaCard(String customerName) {
        //check for blank
        CreditCardBase card = new InfeniaCreditCard(customerName);
        cards.add(card);
        return card;
    }

}