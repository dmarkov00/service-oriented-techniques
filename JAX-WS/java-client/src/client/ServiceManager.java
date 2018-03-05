package client;

import service.Book;
import service.Library;
import service.LibraryService;

import java.util.List;
import java.util.Scanner;

public class ServiceManager {

    private Scanner scan = new Scanner(System.in);

    // Link the library service
    private LibraryService libraryService = new LibraryService();
    private Library library = libraryService.getLibraryPort();

    public void manageInput(String input) {
        List<Book> books;
        Book book;
        switch (input) {
            case "1":

                books = library.getAllBooks();
                System.out.println("Our inventory: ");
                System.out.println();
                for (Book b : books) {
                    System.out.println(b.getName());
                }

                System.out.println();
                System.out.println("Press enter to continue...");

                break;

            case "2":
                System.out.println("Input book name");
                System.out.println();

                String bookName = scan.nextLine();
                book = library.getBookByName(bookName);
                System.out.println("Book data:");
                System.out.println();
                System.out.println("Name: " + book.getName());
                System.out.println("Genre: " + book.getGenre());
                System.out.println("Price: " + book.getPrice());
                System.out.println();
                System.out.println("Press enter to continue...");
                break;
            case "3":
                System.out.println("Input maximum price");
                System.out.println();

                String maxPrice = scan.nextLine();
                books = library.getBooksCheaperThan(Double.parseDouble(maxPrice);
                System.out.println("The books cheaper than " + maxPrice + " euro are:");

                for (Book b : books) {
                    System.out.println(b.getName());
                }

                System.out.println();
                System.out.println("Press enter to continue...");
                break;
            case "4":

                break;
            case "5":

                break;
            case "":
                ConsoleVisualizer.printInstructions();

                break;

            default:
                System.out.println();
                System.out.println("Can not recognize command. Try again.");
        }
    }
}
