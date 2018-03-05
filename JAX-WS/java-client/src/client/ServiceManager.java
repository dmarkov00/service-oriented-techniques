package client;

import service.Book;
import service.Library;
import service.LibraryService;

import java.util.Scanner;

public class ServiceManager {

    private Scanner scan = new Scanner(System.in);

    // Link the library service
    private LibraryService libraryService = new LibraryService();
    private Library library = libraryService.getLibraryPort();

    public void manageInput(String input) {


        switch (input) {
            case "1":
                System.out.println("Insert book name");
                System.out.println();

                String bookName = scan.nextLine();
                Book book = library.getBookByName(bookName);
                System.out.println("Book data");
                System.out.println();
                System.out.println("Name: " + book.getName());
                System.out.println("Genre: " + book.getGenre());
                System.out.println("Price: " + book.getPrice());

                break;

            case "2":

                break;
            case "3":

                break;
            case "4":

                break;
            case "5":

                break;

            default: // Optional
                // Statements
        }
    }
}
