import service.Book;
import service.Library;
import service.LibraryService;
import service.Member;

public class LibraryClient {

    public static void main(String[] args) {
        LibraryService libraryService = new LibraryService();

        Library library = libraryService.getLibraryPort();


        Book b = library.getBookByName("Orange juice");

        System.out.println(b.getName());
    }
}
