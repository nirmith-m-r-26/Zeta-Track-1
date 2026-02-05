public class InfeniaCreditCard extends CreditCardBase implements ICreditCard{

    public InfeniaCreditCard(String customerName) {
        super(customerName);
    }

    @Override
    public boolean transaction(MerchantAccount recipient, float amount) {
        return false;
    }

    @Override
    public boolean withdrawCash(float amount) {
        return false;
    }
}
