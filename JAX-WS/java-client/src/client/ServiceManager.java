package client;

import service.Book;
import service.Library;
import service.LibraryService;
import service.Member;

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
                System.out.println("Press enter to read instructions again...");


                break;

            case "2":
                System.out.println("Input book name:");
                System.out.println();

                String bookName = scan.nextLine();
                book = library.getBookByName(bookName);
                System.out.println("Book data:");
                System.out.println();
                System.out.println("Name: " + book.getName());
                System.out.println("Genre: " + book.getGenre());
                System.out.println("Price: " + book.getPrice());
                System.out.println();
                System.out.println("Press enter to read instructions again...");

                break;
            case "3":
                System.out.println("Input maximum price:");
                System.out.println();

                String maxPrice = scan.nextLine();
                books = library.getBooksCheaperThan(Double.parseDouble(maxPrice));
                System.out.println("The books cheaper than " + maxPrice + " euro are:");

                for (Book b : books) {
                    System.out.println(b.getName());
                }

                System.out.println();
                System.out.println("Press enter to read instructions again...");

                break;
            case "4":
                Member member = new Member();

                System.out.println("Input user name:");
                System.out.println();

                String userName = scan.nextLine();
                member.setName(userName);

                System.out.println("Input password:");
                System.out.println();

                String password = scan.nextLine();
                member.setPassword(password);


                System.out.println("Input user name:");
                System.out.println();

                String age = scan.nextLine();
                member.setAge(Integer.parseInt(age));

                Member newMember = library.registerMember(member);
                System.out.println("Congrats " + newMember.getName() + " for joining the library");

                System.out.println();
                System.out.println("Press enter to read instructions again...");

                break;
            case "5":
                System.out.println("Input user name:");
                System.out.println();
                String userName1 = scan.nextLine();

                System.out.println("Input password:");
                System.out.println();
                String password1 = scan.nextLine();


                Member member1 = library.getMember(userName1, password1);

                System.out.println("Member data:");
                System.out.println();
                System.out.println("Name: " + member1.getName());
                System.out.println("Name: " + member1.getAge());

                System.out.println();
                System.out.println("Press enter to read instructions again...");

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
