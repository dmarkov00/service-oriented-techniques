import service.Book;
import service.Library;
import service.LibraryService;

public class LibraryClient {

    public static void main(String[] args) {
        LibraryService libraryService = new LibraryService();

        Library library = libraryService.getLibraryPort();

        Book b = library.getBookByName("Oven attacks");

        System.out.println(b);
    }
}
