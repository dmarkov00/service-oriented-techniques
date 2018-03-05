package client;

import service.Book;
import service.Library;
import service.LibraryService;
import service.Member;

import java.util.Scanner;

public class LibraryClient {

    public static void main(String[] args) {

        // Link the library service
        LibraryService libraryService = new LibraryService();
        Library library = libraryService.getLibraryPort();

        // Initialization
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        ConsoleVisualizer.printIntroduction();


        Book b = library.getBookByName("Orange juice");

        System.out.println(b.getName());
    }
}
