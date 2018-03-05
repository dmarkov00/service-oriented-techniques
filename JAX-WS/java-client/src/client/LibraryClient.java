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
        ConsoleVisualizer.printIntroduction();
        ConsoleVisualizer.printInstructions();

        

        Book b = library.getBookByName("Orange juice");

        System.out.println(b.getName());
    }
}
