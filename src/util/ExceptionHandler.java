package util;


import java.util.Scanner;

public class ExceptionHandler {


    // Called in removeOrder() when orderID is not found in activeOrders
    public static void handleOrderNotFound(int orderID) {

        // Printer en fejlbesked med ordre ID'et der ikke var fundet.
        System.out.println("Fejl: Ordre #" + orderID + " kan ikke findes.");
    }


    // Vi kalder denne i FileHandler når en fil ikke kan findes.
    public static void handleFileNotFound(String filename) {

        // Printer en fejlbesked med filnavnet der ikke kunne findes.
        System.out.println("Fejl: Filen '" + filename + "' kan ikke findes.");
    }


    // Kaldt i ErrorHandler når input ikke kan parses til den forventede type
    public static void handleInvalidInput(String message) {

        // Printer en fejlbesked med det der er kaldt.
        System.out.println("Fejl: " + message);
    }


    // Kaldt i selectPizzas() metoden når vores Pizza[] array er fuldt.
    public static void handleArrayIndexOutOfBounds(int index) {

        // Printer en fejlbesked når indexet er ude for rækkevide.
        System.out.println("Error: Index " + index + " is out of bounds.");

        // Fortæller brugeren at ordren ikke kan indeholde flere pizzaer.
        System.out.println("The order cannot contain any more pizzas.");
    }


    // Bliver brugt hvis et objekt er null hvor det ikke skal være det.
    public static void handleNullPointer(String context) {

        // Printer en fejlbesked der forklarer i hvilken kontekst null er fundet.
        System.out.println("Fejl: Manglende data i '" + context + "'.");

        // Fortæller brugeren at de skal kontakte system administratoren.
        System.out.println("Venligst kontakt system adminstratoren.");
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
