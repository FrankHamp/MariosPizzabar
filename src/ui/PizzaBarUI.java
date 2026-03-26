package ui;

import model.*;
import service.Order;
import service.OrderHandler;
import util.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class PizzaBarUI {

    private ArrayList<Pizza> menuItems = new ArrayList<>();
    private OrderHandler orderHandler = new OrderHandler();

    public void start() {
        System.out.println(menuItems);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Marios Pizzabar ===");
            System.out.println("1. Show menu");
            System.out.println("2. Add order");
            System.out.println("3. Show orders");
            System.out.println("4. Complete order");
            System.out.println("5. Remove order");
            System.out.println("6. Show specific order");
            System.out.println("7. Exit");

            int choice = ErrorHandler.readInt();

            switch (choice) {
                case 1: showMenu(); break;
                case 2: addOrder(); break;
                case 3: showOrders(); break;
                case 4: completeOrder(); break;
                case 5: removeOrder(); break;
                case 6: showOrder(); break;
                case 7: exitProgram(); running = false; break;
                default: ExceptionHandler.handleInvalidInput(String.valueOf(choice));
            }
        }
    }


    public void showMenu() {
        System.out.println("\n=== Menu ===");
        for (Pizza pizza : menuItems) {
            System.out.println(pizza.toString());
        }
    }

    public void addOrder() {
        // Kundenavn
        System.out.println("\nEnter customer name:");
        String customerName = ErrorHandler.readString();

        // Kundetype
        Customer customer = selectCustomerType(customerName);

        // Vælg pizzaer
        Pizza[] pizzaOrders = selectPizzas();

        // Afhentingstidspunkt
        LocalDateTime pickupTime = selectPickupTime();

        // Opret ordre via OrderHandler
        orderHandler.addOrder(pizzaOrders, customer, pickupTime);
        System.out.println("Order added successfully!");
    }

    private Customer selectCustomerType(String customerName) {
        System.out.println("\nSelect customer type:");
        System.out.println("1. Normal Customer (no discount)");
        System.out.println("2. VIP Customer (10% discount)");
        System.out.println("3. Employee Customer (20% discount)");

        int choice = ErrorHandler.readInt();

        switch (choice) {
            case 2: return new VIPCustomer(customerName);
            case 3: return new EmployeeCustomer(customerName);
            default: return new NormalCustomer(customerName);
        }
    }
// er loadMenu ikke overflødig, når der er noget lignende i selectPizzas?

    private void loadMenu() {
        FileHandler fileHandler = new FileHandler();
        menuItems = fileHandler.loadMenu();
    }

    private Pizza[] selectPizzas () {

        //måske ikke menuItems
            Pizza[] pizzaOrders = new Pizza[10];
            int count = 0;

            showMenu();
            System.out.println("\nEnter pizza number (0 to finish):");

            while (count < pizzaOrders.length) {
                int pizzaNumber = ErrorHandler.readInt();
                if (pizzaNumber == 0) break;

                Pizza selected = findPizza(pizzaNumber);
                if (selected != null) {
                    pizzaOrders[count] = selected;
                    count++;
                    System.out.println("Added: " + selected.getName() + " - " + selected.getPrice() + "kr");
                } else {
                    ExceptionHandler.handleInvalidInput(String.valueOf(pizzaNumber));
                }
            }
            return pizzaOrders;
        }

        private Pizza findPizza ( int pizzaNumber) {

                for (Pizza pizza : menuItems) {
                    if (pizza.getNumber() == pizzaNumber) { // bruger getNumber() fra Pizza klassen
                        return pizza;
                    }
                }
                return null;
    }

        private LocalDateTime selectPickupTime () {
            System.out.println("Enter pickup hour (HH):");
            int hour = ErrorHandler.readInt();
            System.out.println("Enter pickup minute (MM):");
            int minute = ErrorHandler.readInt();

            return LocalDateTime.of(
                    LocalDateTime.now().toLocalDate(),
                    LocalTime.of(hour, minute)
            );
        }

    public void showOrders() {
        orderHandler.sortOrdersByTime();
        ArrayList<Order> activeOrders = orderHandler.getActiveOrders();

        if (activeOrders.isEmpty()) {
            System.out.println("\nNo active orders.");
            return;
        }

        System.out.println("\n=== Active Orders ===");
        for (Order order : activeOrders) {
            System.out.println(order.toString());
        }
    }

    public void removeOrder() {
        System.out.println("Enter order ID to remove:");
        int orderID = ErrorHandler.readInt();
        orderHandler.removeOrder(orderID);
        System.out.println("Order #" + orderID + " removed.");
    }

    public void completeOrder() {
        System.out.println("Enter order ID to complete:");
        int orderID = ErrorHandler.readInt();
        orderHandler.completeOrder(orderID);
        System.out.println("Order #" + orderID + " completed and saved.");
    }

    public void showOrder() {
        System.out.println("Enter order ID to show:");
        int orderID = ErrorHandler.readInt();

        for (Order order : orderHandler.getActiveOrders()) {
            if (order.getOrderID() == orderID) {
                System.out.println(order.toString());
                return;
            }
        }
        ExceptionHandler.handleOrderNotFound(orderID);
    }

    public void clearOrders() {
        orderHandler.getActiveOrders().clear();
        System.out.println("All orders cleared.");
    }

    public void exitProgram() {
        System.out.println("Goodbye!");
    }
}
