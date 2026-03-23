package model;

public class EmployeeCustomer extends Customer {

    public EmployeeCustomer(String customerName) {
        super(customerName); // Kalder på konstruktøren af Customer
    }


    /*
    Overrider udregningen af pris i Order og tilføjer 20% rabat
    I super klassen bliver total prisen * 1
    her vel 1 - 0.20 = 0.80, hvilket vil sige, at prisen bliver ganget med 0.80 i stedet for 1
    derved er der blevet trukket 20% af prisen
     */

    @Override
    public double getDiscountRate(){
        return 0.20;
    }
}
