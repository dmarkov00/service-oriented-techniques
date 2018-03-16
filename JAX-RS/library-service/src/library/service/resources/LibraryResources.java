package library.service.resources;

import library.models.Book;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
    @Produces(MediaType.APPLICATION_JSON)
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

    public void addBook(Book book) {

    }

    public Book getBookById(int id) {

        return null;
    }

    public void updateBookById(Book books) {

    }

    public void deleteBookById(int id) {

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
