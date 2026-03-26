package ui;

import file.FileHandler;
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
        loadMenu();
        System.out.println(menuItems);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Marios Pizzabar ===");
            System.out.println("1. Vis Menu");
            System.out.println("2. Tilføj Ordre");
            System.out.println("3. Vis Ordre");
            System.out.println("4. Færdiggør Ordre");
            System.out.println("5. Fjern Ordre");
            System.out.println("6. Vis en specifik ordre");
            System.out.println("7. Afslut");

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
        System.out.println("\nIndtast kundens navn:");
        String customerName = ErrorHandler.readString();

        // Kundetype
        Customer customer = selectCustomerType(customerName);

        // Vælg pizzaer
        Pizza[] pizzaOrders = selectPizzas();

        // Afhentingstidspunkt
        LocalDateTime pickupTime = selectPickupTime();

        // Opret ordre via OrderHandler
        orderHandler.addOrder(pizzaOrders, customer, pickupTime);
        System.out.println("Ordren blev tilføjet!");
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

    private Pizza[] selectPizzas() {
        System.out.println("\nHvor mange pizzaer skal bestilles?");
        int amount = ErrorHandler.readInt();

        // Opretter arrayet med præcis den størrelse Alfonso indtaster
        Pizza[] pizzaOrders = new Pizza[amount];
        int count = 0;

        showMenu();
        System.out.println("\nIndtast pizza nummeret:");

        while (count < pizzaOrders.length) {
            int pizzaNumber = ErrorHandler.readInt();

            Pizza selected = findPizza(pizzaNumber);
            if (selected != null) {
                pizzaOrders[count] = selected;
                count++;
                System.out.println("Tilføjet: " + selected.getName() + " - " + selected.getPrice() + "kr");
            } else {
                ExceptionHandler.handleInvalidInput(String.valueOf(pizzaNumber));
            }
        }
        return pizzaOrders;
    }






//    private Pizza[] selectPizzas () {
//
//        //måske ikke menuItems
//            Pizza[] pizzaOrders = new Pizza[10];
//            int count = 0;
//
//            showMenu();
//            System.out.println("\nEnter pizza number (0 to finish):");
//
//            while (count < pizzaOrders.length) {
//                int pizzaNumber = ErrorHandler.readInt();
//                if (pizzaNumber == 0) break;
//
//                Pizza selected = findPizza(pizzaNumber);
//                if (selected != null) {
//                    pizzaOrders[count] = selected;
//                    count++;
//                    System.out.println("Added: " + selected.getName() + " - " + selected.getPrice() + "kr");
//                } else {
//                    ExceptionHandler.handleInvalidInput(String.valueOf(pizzaNumber));
//                }
//            }
//            return pizzaOrders;
//        }

        private Pizza findPizza ( int pizzaNumber) {

                for (Pizza pizza : menuItems) {
                    if (pizza.getNumber() == pizzaNumber) { // bruger getNumber() fra Pizza klassen
                        return pizza;
                    }
                }
                return null;
    }

        private LocalDateTime selectPickupTime () {
            System.out.println("\nIndtast afhentnings-time (TT):");
            int hour = ErrorHandler.readInt();
            System.out.println("Indtast afhentnings-minut (MM):");
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
            System.out.println("\nIngen aktive ordre.");
            return;
        }

        System.out.println("\n=== Aktive Ordre ===");
        for (Order order : activeOrders) {
            System.out.println(order.toString());
        }
    }

    public void removeOrder() {
        System.out.println("Indtast det ordre ID du vil fjerne");
        int orderID = ErrorHandler.readInt();
        orderHandler.removeOrder(orderID);
        System.out.println("Order #" + orderID + " removed.");
    }

    public void completeOrder() {
        System.out.println("Indtast det ordre ID du vil fuldføre: ");
        int orderID = ErrorHandler.readInt();
        orderHandler.completeOrder(orderID);
        System.out.println("Order #" + orderID + " fuldført og gemt.");
    }

    public void showOrder() {
        System.out.println("Indtast ordre ID for at vise: ");
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
        System.out.println("Alle ordre blev clearet.");
    }

    public void exitProgram() {
        System.out.println("Systemet lukker nu ned, tak for denne gang!");
    }
}
