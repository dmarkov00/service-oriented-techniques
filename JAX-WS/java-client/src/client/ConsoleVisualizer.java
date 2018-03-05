package client;

public class ConsoleVisualizer {

    public static void printIntroduction() {

        System.out.println("Welcome the the newest virtual library!");
        System.out.println("----------------------------------------------------");
        System.out.println();
        System.out.println("What functionality do we provide?");
        System.out.println();
        System.out.println("-You can see all the books in the library.");
        System.out.println("-Get more information about book by inputting the name.");
        System.out.println("-Get books that are cheaper than certain price.");
        System.out.println("-Register as a member.");
        System.out.println("-Retrieve personal information by entering username and password.");
        System.out.println("----------------------------------------------------");
        System.out.println();

    }

    public static void printInstructions() {
        System.out.println("Instructions for operation:");
        System.out.println("----------------------------------------------------");
        System.out.println();

        System.out.println("Press 1 - to retrieve all books in the library");
        System.out.println("Press 2: - to get more information about a certain book");

        System.out.println("Press 3: - to get books cheaper than certain price");

        System.out.println("Press 4: - to register a member");

        System.out.println("Press 5: - to retrieve personal member information");

        System.out.println();
        System.out.println("Waiting for input...");



    }
}
