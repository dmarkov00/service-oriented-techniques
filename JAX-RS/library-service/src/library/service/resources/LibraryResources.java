package library.service.resources;

import library.models.Book;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("library")
@Singleton
public class LibraryResources {

    public LibraryResources() {
        books = new ArrayList<>(Arrays.asList(
                new Book("Ghost", "Horror", 22.5),
                new Book("Pirate", "Comedy", 22.5),
                new Book("The ship", "Adventure", 22.5),
                new Book("Dogs", "Drama", 22.5)));
    }

    private List<Book> books;


    @GET()
//    @Path("hello")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAllBooks() {
        return books;
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

    public List<Book> filterBooksByGenre(String genre) {
        return null;
    }

    public List<Book> filterBooksByPrice(double price) {
        return null;
    }

    public List<Book> filerBooksByGenreAndPrice(String genre, double price) {
        return null;
    }


}
