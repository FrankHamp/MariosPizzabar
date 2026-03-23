package service;

//Importerer alt fra model packagen
import model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {

    //Variable
    private int orderID;
    private LocalDateTime pickupTime;
    private Pizza[] pizzaOrders;
    private Customer customer;
    private OrderStatus status;

    //Constructor
    public Order(int orderID, LocalDateTime pickupTime, Pizza[] pizzaOrders, Customer customer, OrderStatus status) {
        this.orderID = orderID;
        this.pickupTime = pickupTime;
        this.pizzaOrders = pizzaOrders;
        this.customer = customer;
        this.status = OrderStatus.ORDER_PLACED;
    }

    //Metode til at beregne den totale pris
    public double calculateTotalPrice() {
        double total = 0;               //initialiserer en double vi kalder total der starter på 0

        for (Pizza p : pizzaOrders) {
            if (p != null) {            //Hvis p ikke returnere null
                total += p.getPrice();    // så plusser vi total der er 0 med pizzaens pris med getPrice() fra Pizza Klassen.
            }
        }                               // returnere totalprisen og ganger den med rabat fra customerType
        return total * (1 - customer.getDiscountRate());
    }

    //Setters
    public void setPickupTime(LocalDateTime pickupTime){
        this.pickupTime = pickupTime;
    }

    public void setStatus(OrderStatus status){
        this.status = status;
    }

    //Getters
    public int getOrderID() {
        return orderID;
    }

    public LocalDateTime getPickupTime() {
        return pickupTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Pizza[] getPizzaOrders() {
        return pizzaOrders;
    }

    public OrderStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return  "Ordre #" + orderID +
                " | Afhentning: " + pickupTime.format(DateTimeFormatter.ofPattern("HH:mm")) +
                " | Kunde: " + customer.getCustomerName() +
                " | Status: " + status +
                " | Total: " + calculateTotalPrice() + "kr";;
    }
}
