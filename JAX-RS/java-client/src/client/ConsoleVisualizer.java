package client;

public class ConsoleVisualizer {
    public static void printIntroduction() {

        System.out.println("Welcome the the ADMIN panel of the virtual library");
        System.out.println("----------------------------------------------------");
        System.out.println();
        System.out.println("What functionality is provided?");
        System.out.println();
        System.out.println("-You can retrieve all the books in the library.");
        System.out.println("-You can add a new book to the library.");
        System.out.println("-Get more information about a book by inputting the id.");
        System.out.println("-You can update a book by id.");
        System.out.println("-You can delete a book by id.");
        System.out.println("-Filter books by price.");
        System.out.println("-Filter books by genre.");
        System.out.println("-Filter books by price and genre.");


        System.out.println("----------------------------------------------------");
        System.out.println();

    }

    public static void printInstructions() {
        System.out.println("Instructions for operation:");
        System.out.println("----------------------------------------------------");
        System.out.println();

        System.out.println("Press 1: - to retrieve all books in the library");

        System.out.println("Press 2: - to create a new book");

        System.out.println("Press 3: - to get more information about a certain book");

        System.out.println("Press 4: - to update a book");

        System.out.println("Press 5: - to delete a book");

        System.out.println("Press 6: - to filter books by genre");

        System.out.println("Press 7: - to filter books by price");

        System.out.println("Press 8: - to filter by price and genre");

        System.out.println();
        System.out.println("Waiting for input...");


    }
    public static void howToProceedInstructions(){
        System.out.println();
        System.out.println("Press enter to read instructions again or proceed with new input...");
    }
}
