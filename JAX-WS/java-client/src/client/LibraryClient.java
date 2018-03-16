package client;

import service.Book;
import service.Library;
import service.LibraryService;
import service.Member;

import java.util.Scanner;

public class LibraryClient {

    public static void main(String[] args) {

        ServiceManager serviceManager = new ServiceManager();

        // Initialization
        ConsoleVisualizer.printIntroduction();
        ConsoleVisualizer.printInstructions();
        Scanner scan = new Scanner(System.in);

        // Scanning for values from the console
        while (true) {
            String input = scan.nextLine();
            serviceManager.manageInput(input);
        }

    }
}
