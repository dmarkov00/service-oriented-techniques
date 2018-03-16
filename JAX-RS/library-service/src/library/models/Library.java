package library.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Library {

    public Library() {
        books = new ArrayList<>(Arrays.asList(
                new Book("Ghost", "Horror", 22.5),
                new Book("Pirate", "Comedy", 22.5),
                new Book("The ship", "Adventure", 22.5),
                new Book("Dogs", "Drama", 22.5)));
    }

    private List<Book> books;

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

    }

    public List<Book> filterBooksByPrice(double price) {

    }

    public List<Book> filerBooksByGenreAndPrice(String genre, double price) {

    }


}
