package model;

public class NormalCustomer extends Customer {

    public NormalCustomer(String customerName) {
        super(customerName); // Kalder på konstruktøren af Customer
    }

    /*
     Overrider udregningen af pris i Order
     I super klassen bliver total prisen * 1
     her er det 1 - 0.0. = 1, hvilket vil sige, at prisen bliver ganget med 1
     derved er der ikke blevet trukket rabat
     */

@Override
    public double getDiscountRate(){
        return 0.0;
}

}
