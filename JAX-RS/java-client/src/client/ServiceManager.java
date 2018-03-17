package client;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import java.util.List;
import java.util.Scanner;

import library.models.Book;

public class ServiceManager {
    private Scanner scan = new Scanner(System.in);

    // Link the library service
    private WebTarget serviceTarget = ConfigureClient.configure();


    public void manageInput(String input) {

        switch (input) {
            case "1":
                getBooks();

                break;

            case "2":

                break;
            case "3":

                getBookById();

                break;
            case "4":


                break;
            case "5":


                break;
            case "6":


                break;
            case "":
                ConsoleVisualizer.printInstructions();

                break;

            default:
                System.out.println();
                System.out.println("Can not recognize command. Try again.");
        }
    }

    private List<Book> getBooks() {

        System.out.println("Our inventory: ");
        System.out.println();

        WebTarget operationTarget = serviceTarget;

        // build the request: media type plain text
        Invocation.Builder requestBuilder = operationTarget.request().accept(MediaType.APPLICATION_JSON);

        // execute operation get
        Response response = requestBuilder.get();

        // read the result answer from the http response
        String result = response.readEntity(String.class);
//        ConsoleVisualizer.howToProceedInstructions();

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            System.out.println(result);
        } else {
            System.err.println(result);
        }
        return null;
    }

    private void getBookById() {
        System.out.println("Input book id:");
        System.out.println();
        String bookId = scan.nextLine();

        WebTarget operationTarget = serviceTarget.path(bookId);

        Invocation.Builder requestBuilder = operationTarget.request().accept(MediaType.APPLICATION_XML);

        Response response = requestBuilder.get();

        Book result = response.readEntity(Book.class);

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {

            System.out.println(result.toString());
        } else {
            System.err.println(response.readEntity(String.class));
        }

        ConsoleVisualizer.howToProceedInstructions();
    }

    private void addBook() {
        System.out.println("Input book title:");
        System.out.println();
        String bookTitle = scan.nextLine();
        System.out.println("Input book genre:");
        System.out.println();
        String bookGenre = scan.nextLine();

        System.out.println("Input book price:");
        System.out.println();
        String bookPrice = scan.nextLine();
        Book book;
        try {
            book = new Book(bookTitle, bookGenre, Double.parseDouble(bookPrice));

        } catch (NumberFormatException e) {
            System.out.println("You didn't enter a proper price value.");
            ConsoleVisualizer.howToProceedInstructions();
            return;
        }
        Response response = serviceTarget.request()
                .accept(MediaType.TEXT_PLAIN)
                .post(Entity.entity(book, MediaType.APPLICATION_JSON));

        if (response.getStatus() == Response.Status.NO_CONTENT.getStatusCode()) {

            System.out.println("Book " + book.getTitle() + " was added.");
        } else {
            System.err.println(response.readEntity(String.class));
        }

        ConsoleVisualizer.howToProceedInstructions();
    }
}
