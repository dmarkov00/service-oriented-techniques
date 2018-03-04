package library;

import models.Book;
import models.Member;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface LibraryService {

    @WebMethod
    List<Book> getAllBooks();

    @WebMethod
    Book getBookByName(String bookName);

    @WebMethod
    List<Book> getBooksCheaperThan(double price);

    @WebMethod
    Member registerMember(Member member);

    @WebMethod
    Member getMemberByID(int memberID);

    @WebMethod
    List<Book> filterByGenre(String genre);

}
