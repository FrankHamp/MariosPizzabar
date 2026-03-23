package model;

public class VIPCustomer extends Customer {

    public VIPCustomer(String customerName) {
        super(customerName); // Kalder på konstruktøren af Customer
    }


    /*
     Overrider udregningen af pris i Order
     I Order bliver total prisen * 1
     her er det 1 - 0.10. = 0.90, hvilket vil sige, at prisen bliver ganget med 0.90
     derved er der tilføjet 10% rabat
     */

    @Override
    public double getDiscountRate(){
        return 0.10;
    }
}
