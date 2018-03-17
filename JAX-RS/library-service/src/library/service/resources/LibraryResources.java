package library.service.resources;

import library.models.Book;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("library")
@Singleton
public class LibraryResources {

    public LibraryResources() {
        books = new ArrayList<>(Arrays.asList(
                new Book(1, "Ghost", "Horror", 96),
                new Book(2, "Pirate", "Comedy", 100),
                new Book(3, "The ship", "Adventure", 21.5),
                new Book(4, "Dogs", "Drama", 22.5)));
    }

    private List<Book> books;


    @GET()
    @Path("books")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Book> getAllBooks(@QueryParam("genre") String genre, @QueryParam("price") double price) {
        List<Book> booksResult = this.books;
        if (genre != null) {
            booksResult = filterBooksByGenre(genre, booksResult);
        }
        if (price != 0.0d) {
            booksResult = filterBooksByPrice(price, booksResult);
        }
        return booksResult;
    }

    @POST
    @Path("books")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void addBook(Book book) {
        Book bookValidation = getBookWithId(book.getId());
        if (bookValidation == null) {
            this.books.add(book);
        } else {
            throw new RuntimeException("Book with such id already exists");
        }
    }

    @GET()
    @Path("books/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Book getBookById(@PathParam("id") int id) {
        Book book = getBookWithId(id);
        if (book != null) {
            return book;
        } else {
            throw new RuntimeException("Book with this id does not exist");
        }
    }

    @POST
    @Path("books/{id}")
    public void updateBookById(Book books) {

    }

    @DELETE
    @Path("books/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public void deleteBookById(@PathParam("id") int id) {
        Book book = getBookWithId(id);
        if (book != null) {
            books.remove(id);
        } else {
            throw new RuntimeException("Book with this id does not exist");
        }
    }


    private List<Book> filterBooksByGenre(String genre, List<Book> books) {

        List<Book> filteredBooksByGenre = new ArrayList<>();
        for (Book book : books) {
            if (book.getGenre().toLowerCase().equals(genre.toLowerCase())) {
                filteredBooksByGenre.add(book);
            }
        }
        return filteredBooksByGenre;
    }

    private List<Book> filterBooksByPrice(double price, List<Book> books) {

        List<Book> filteredBooksByPrice = new ArrayList<>();
        for (Book book : books) {
            if (book.getPrice() < price) {
                filteredBooksByPrice.add(book);
            }
        }
        return filteredBooksByPrice;
    }

    private Book getBookWithId(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }


}
