package model;

public abstract class Customer {

    private String customerName;

    public Customer(String customerName) {
    /*
Hvis Alfonso ikke udfylder et navn, vil programmet bede ham om at udfylde et navn
 */

    if(customerName ==null||customerName.isBlank()) {
        throw new IllegalArgumentException("Kunde navn kan være ikke tomt, udfyld feltet. ");}

        this.customerName = customerName.trim();
    }

    public String getCustomerName() {
        return customerName; }


    //Henter rabatten fra subklasserne - bliver derefter regnet ud i Order
public abstract double getDiscountRate();


}
