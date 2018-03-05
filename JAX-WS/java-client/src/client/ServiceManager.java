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

        switch (input) {
            case "1":
                getAllBooks();

                break;

            case "2":
                getBookByName();

                break;
            case "3":
                getBooksCheaperThan();

                break;
            case "4":
                filterByGenre();

                break;
            case "5":
                registerMember();

                break;
            case "6":
                getMember();

                break;
            case "":
                ConsoleVisualizer.printInstructions();

                break;

            default:
                System.out.println();
                System.out.println("Can not recognize command. Try again.");
        }
    }

    private void getAllBooks() {
        List<Book> books = library.getAllBooks();
        System.out.println("Our inventory: ");
        System.out.println();
        for (Book book : books) {
            System.out.println(book.getName());
        }

        System.out.println();
        System.out.println("Press enter to read instructions again or proceed with new input...");
    }

    private void getBookByName() {
        System.out.println("Input book name:");
        System.out.println();

        String bookName = scan.nextLine();
        Book book = library.getBookByName(bookName);

        if (book != null) {
            System.out.println("Book data:");
            System.out.println();
            System.out.println("Name: " + book.getName());
            System.out.println("Genre: " + book.getGenre());
            System.out.println("Price: " + book.getPrice());

        } else {
            System.out.println("Nothing found.");
        }
        System.out.println();
        System.out.println("Press enter to read instructions again or proceed with new input...");

    }

    private void getBooksCheaperThan() {
        System.out.println("Input maximum price:");
        System.out.println();

        List<Book> books;
        String maxPrice = scan.nextLine();
        try {
            books = library.getBooksCheaperThan(Double.parseDouble(maxPrice));

        } catch (NumberFormatException e) {
            System.out.println("You didn't enter a proper number.");
            System.out.println();
            System.out.println("Press enter to read instructions again or proceed with new input...");
            return;
        }


        if (books != null) {
            System.out.println("The books cheaper than " + maxPrice + " euro are:");
            System.out.println();

            for (Book book : books) {
                System.out.println(book.getName());
            }
        } else {
            System.out.println("We don't sell that cheap books.");
        }


        System.out.println();
        System.out.println("Press enter to read instructions again or proceed with new input...");

    }

    private void registerMember() {
        Member member = new Member();


        System.out.println("Input user name:");
        System.out.println();

        String userName = scan.nextLine();
        member.setName(userName);

        System.out.println("Input password:");
        System.out.println();

        String password = scan.nextLine();
        member.setPassword(password);


        System.out.println("Input age:");
        System.out.println();

        String age = scan.nextLine();
        try {
            member.setAge(Integer.parseInt(age));
        } catch (NumberFormatException e) {
            System.out.println("You didn't enter a proper number.");
            System.out.println();
            System.out.println("Press enter to read instructions again or proceed with new input...");
            return;
        }


        Member newMember = library.registerMember(member);
        if (newMember != null) {
            System.out.println("Congrats " + newMember.getName() + " for joining the library.");
        } else {
            System.out.println("Error while registering.");
        }

        System.out.println();
        System.out.println("Press enter to read instructions again or proceed with new input...");

    }

    private void getMember() {
        System.out.println("Input user name:");
        System.out.println();
        String userName = scan.nextLine();

        System.out.println("Input password:");
        System.out.println();
        String password = scan.nextLine();


        Member member = library.getMember(userName, password);
        if (member != null) {


            System.out.println("Member data:");
            System.out.println();
            System.out.println("Name: " + member.getName());
            System.out.println("Age: " + member.getAge());
        }else{
            System.out.println("No such member.");
        }
        System.out.println();
        System.out.println("Press enter to read instructions again or proceed with new input...");

    }

    private void filterByGenre() {
        System.out.println("Input genre:");
        System.out.println();
        String genre = scan.nextLine();
        List<Book> books = library.filterByGenre(genre);
        System.out.println("Our books with genre " + genre + " are.");
        System.out.println();

        for (Book book : books) {
            System.out.println(book.getName());
        }

        System.out.println();
        System.out.println("Press enter to read instructions again or proceed with new input...");

    }
}
