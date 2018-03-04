package library;

import models.Book;
import models.Member;

import java.util.List;

public interface LibraryService {


    List<Book> getAllBooks();

    Book getBookByName(String bookName);

    List<Book> getBooksCheaperThan(double price);

    Member registerMember(Member member);

    Member getMemberByID(int memberID);

    List<Book> filterByGenre(String genre);

}
