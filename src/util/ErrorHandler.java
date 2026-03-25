package util;

import java.util.Scanner;

public class ErrorHandler {

    public static String readString() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input != null && !input.isBlank()) {
                return input.trim();
            }
            ExceptionHandler.handleInvalidInput("Feltet kan ikke være tomt, prøv igen: ");
        }
    }

    public static int readInt() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                ExceptionHandler.handleInvalidInput("Det indtastede er ikke et nummer, prøv igen: ");
            }
        }
    }
}
