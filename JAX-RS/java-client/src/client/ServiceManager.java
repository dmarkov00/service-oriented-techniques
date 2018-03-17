package client;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import java.util.ArrayList;
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
                addBook();
                break;
            case "3":

                getBookById();

                break;
            case "4":


                break;
            case "5":


                break;
            case "6":
                filterByGenre();
                break;
            case "7":
                filterByPrice();
                break;
            case "8":
                filterByGenreAndPrice();
                break;
            case "":
                ConsoleVisualizer.printInstructions();

                break;

            default:
                System.out.println();
                System.out.println("Can not recognize command. Try again.");
        }
    }

    private void getBooks() {

        System.out.println("Our inventory: ");
        System.out.println();

        Response response = serviceTarget.request().accept(MediaType.APPLICATION_JSON).get();

        sendGetBooksRequest(response);
        ConsoleVisualizer.howToProceedInstructions();
    }

    private void getBookById() {
        System.out.println("Input book id:");
        System.out.println();
        String bookId = scan.nextLine();

        Response response = serviceTarget.path(bookId).request().accept(MediaType.APPLICATION_JSON).get();

        Book result = response.readEntity(Book.class);

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            System.out.println("Resulted book:");
            System.out.println();
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

    private void deleteBook(){

    }

    private void filterByGenre() {
        System.out.println("Input genre:");
        System.out.println();
        String genre = scan.nextLine();


        Response response = serviceTarget.queryParam("genre", genre).request().accept(MediaType.APPLICATION_JSON).get();

        System.out.println("Our inventory: filtered by genre: ");
        System.out.println();

        sendGetBooksRequest(response);

        ConsoleVisualizer.howToProceedInstructions();

    }

    private void sendGetBooksRequest(Response response) {
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {

            GenericType<ArrayList<Book>> genericType = new GenericType<ArrayList<Book>>() {
            };

            ArrayList<Book> booksList = response.readEntity(genericType);
            for (Book book : booksList) {
                System.out.println(book.toString());
            }

        } else {
            System.err.println(response.readEntity(String.class));
        }
    }

    private void filterByPrice() {
        System.out.println("Input price:");
        System.out.println();
        String price = scan.nextLine();


        Response response = serviceTarget.queryParam("price", price).request().accept(MediaType.APPLICATION_JSON).get();

        System.out.println("Our inventory: filtered by price: ");
        System.out.println();

        sendGetBooksRequest(response);

        ConsoleVisualizer.howToProceedInstructions();
    }

    private void filterByGenreAndPrice() {
        System.out.println("Input genre:");
        System.out.println();
        String genre = scan.nextLine();

        System.out.println("Input price:");
        System.out.println();
        String price = scan.nextLine();


        Response response = serviceTarget.queryParam("genre", genre).queryParam("price", price).request().accept(MediaType.APPLICATION_JSON).get();

        System.out.println("Our inventory: filtered by price: ");
        System.out.println();

        sendGetBooksRequest(response);

        ConsoleVisualizer.howToProceedInstructions();

    }
}
