package util;

public class ExceptionHandler {
    public static void handleOrderNotFound(int orderID) {
        System.out.println("Fejl: Order #" + orderID + " kunne ikke findes.");
    }
}
