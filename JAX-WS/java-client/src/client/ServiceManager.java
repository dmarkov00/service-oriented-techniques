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

        switch (input) {
            case "1":

                List<Book> books = library.getAllBooks();
                System.out.println("Our inventory: ");
                System.out.println();
                for (Book book : books) {
                    System.out.println(book.getName());
                }

                System.out.println();
                System.out.println("Press enter to continue...");

                break;

            case "2":
                System.out.println("Input book name");
                System.out.println();

                String bookName = scan.nextLine();
                Book book = library.getBookByName(bookName);
                System.out.println("Book data:");
                System.out.println();
                System.out.println("Name: " + book.getName());
                System.out.println("Genre: " + book.getGenre());
                System.out.println("Price: " + book.getPrice());
                System.out.println();
                System.out.println("Press enter to continue...");
                break;
            case "3":

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
