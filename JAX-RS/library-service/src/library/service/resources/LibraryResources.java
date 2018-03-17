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
                new Book("Ghost", "Horror", 96),
                new Book("Pirate", "Comedy", 100),
                new Book("The ship", "Adventure", 21.5),
                new Book("Dogs", "Drama", 22.5)));
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
        this.books.add(book);
    }

    @GET()
    @Path("books/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Book getBookById(@PathParam("id") int id) {

        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        throw new RuntimeException("Book with this id does not exist");
    }

    @POST
    @Path("books/{id}")
    public void updateBookById(Book books) {

    }

    public void deleteBookById(int id) {
        books.remove(id);
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


}
