package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;

public class ExceptionHandler {
    public static void handleOrderNotFound(int orderID) {
        System.out.println("Fejl: Order #" + orderID + " kunne ikke findes.");
    }

    public static void handleInputMismatch(String input) {
        System.out.println("Fejl: " + input + " er ikke tilladt");

    }

    public static void handleInputMismatch(InputMismatchException e) {
        System.out.println("Fejl: Ugyldig input");

    }

    public static void handleIO(IOException e) {
        System.out.println("Fejl: filen du søger eksisterer ikke");
    }


}
