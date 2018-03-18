package client;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.Scanner;

import library.models.Book;

public class ServiceManager {
    private Scanner scan = new Scanner(System.in);

    // Link the service
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
                updateBookById();
                break;
            case "5":
                deleteBook();
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
            case "9":
                addPersonToBannedFromLibraryList();
                break;
            case "":
                ConsoleVisualizer.printInstructions();

                break;

            default:
                System.out.println();
                System.out.println("Can not recognize command. Try again.");
        }
    }

    private void addPersonToBannedFromLibraryList() {
        // Making use of the Form class
        System.out.println("Input person name:");
        System.out.println();
        String personName = scan.nextLine();

        Form form = new Form();

        form.param("name", personName);

        Entity<Form> entity = Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED);

        Response response = serviceTarget.path("ban").request()
                .accept(MediaType.TEXT_PLAIN)
                .post(entity);

        if (response.getStatus() == Response.Status.NO_CONTENT.getStatusCode()) {
            System.out.println(personName + " - was banned from the library");
        } else {
            System.err.println(response.readEntity(String.class));
        }

        ConsoleVisualizer.howToProceedInstructions();


    }

    private void updateBookById() {
        System.out.println("Input the id of the book you want to update:");
        System.out.println();
        String bookId = scan.nextLine();
        int bookIdInt;
        try {
            bookIdInt = Integer.parseInt(bookId);

        } catch (NumberFormatException e) {
            System.out.println("You didn't enter a proper id value (has to be an integer).");
            ConsoleVisualizer.howToProceedInstructions();
            return;
        }
        System.out.println("Add the new values:");
        System.out.println();

        System.out.println("Input book id:");
        System.out.println();
        String bookupdateId = scan.nextLine();
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
            book = new Book(Integer.parseInt(bookupdateId), bookTitle, bookGenre, Double.parseDouble(bookPrice));

        } catch (NumberFormatException e) {
            System.out.println("You didn't enter a proper id or price values.");
            ConsoleVisualizer.howToProceedInstructions();
            return;
        }
        Response response = serviceTarget.path(bookIdInt + "").request()
                .accept(MediaType.TEXT_PLAIN)
                .put(Entity.entity(book, MediaType.APPLICATION_XML));

        if (response.getStatus() == Response.Status.NO_CONTENT.getStatusCode()) {

            System.out.println("Book " + book.getTitle() + " was updated.");
        } else {
            System.err.println(response.readEntity(String.class));
        }

        ConsoleVisualizer.howToProceedInstructions();

    }

    private void getBooks() {

        System.out.println("Our inventory: ");
        System.out.println();

        Response response = serviceTarget.request().accept(MediaType.APPLICATION_XML).get();

        parseGetBooksResponse(response);
        ConsoleVisualizer.howToProceedInstructions();
    }

    private void getBookById() {
        System.out.println("Input book id:");
        System.out.println();
        String bookId = scan.nextLine();
        int bookIdInt;
        try {
            bookIdInt = Integer.parseInt(bookId);

        } catch (NumberFormatException e) {
            System.out.println("You didn't enter a proper id value(has to be an integer).");
            ConsoleVisualizer.howToProceedInstructions();
            return;
        }

        Response response = serviceTarget.path(bookIdInt + "").request().accept(MediaType.APPLICATION_JSON)
                .get();


        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            Book result = response.readEntity(Book.class);

            System.out.println("Resulted book:");
            System.out.println();
            System.out.println(result.toString());
        } else {
            System.err.println(response.readEntity(String.class));
        }

        ConsoleVisualizer.howToProceedInstructions();
    }

    private void addBook() {
        System.out.println("Input book id:");
        System.out.println();
        String bookId = scan.nextLine();
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
            book = new Book(Integer.parseInt(bookId), bookTitle, bookGenre, Double.parseDouble(bookPrice));

        } catch (NumberFormatException e) {
            System.out.println("You didn't enter a proper id or price values. ");
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

    private void deleteBook() {
        System.out.println("Input book id:");
        System.out.println();
        String bookId = scan.nextLine();
        int bookIdInt;
        try {
            bookIdInt = Integer.parseInt(bookId);

        } catch (NumberFormatException e) {
            System.out.println("You didn't enter a proper id value(has to be an integer).");
            ConsoleVisualizer.howToProceedInstructions();
            return;
        }

        Response response = serviceTarget.path(bookIdInt + "").request().accept(MediaType.TEXT_PLAIN).delete();
        if (response.getStatus() == Response.Status.NO_CONTENT.getStatusCode()) {
            System.out.println("Book deleted!");
        } else {
            System.err.println(response.readEntity(String.class));
        }
        ConsoleVisualizer.howToProceedInstructions();

    }

    private void filterByGenre() {
        System.out.println("Input genre:");
        System.out.println();
        String genre = scan.nextLine();


        Response response = serviceTarget.queryParam("genre", genre).request().accept(MediaType.APPLICATION_JSON).get();

        System.out.println("Our inventory: filtered by genre: ");
        System.out.println();

        parseGetBooksResponse(response);

        ConsoleVisualizer.howToProceedInstructions();

    }

    private void filterByPrice() {
        System.out.println("Input price:");
        System.out.println();
        String price = scan.nextLine();


        Response response = serviceTarget.queryParam("price", price).request().accept(MediaType.APPLICATION_JSON).get();

        System.out.println("Our inventory: filtered by price: ");
        System.out.println();

        parseGetBooksResponse(response);

        ConsoleVisualizer.howToProceedInstructions();
    }

    private void filterByGenreAndPrice() {
        System.out.println("Input genre:");
        System.out.println();
        String genre = scan.nextLine();

        System.out.println("Input price:");
        System.out.println();
        String price = scan.nextLine();


        Response response = serviceTarget.queryParam("genre", genre).queryParam("price", price).request().accept(MediaType.APPLICATION_XML).get();

        System.out.println("Our inventory: filtered by genre and price: ");
        System.out.println();

        parseGetBooksResponse(response);

        ConsoleVisualizer.howToProceedInstructions();

    }

    private void parseGetBooksResponse(Response response) {
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

}
