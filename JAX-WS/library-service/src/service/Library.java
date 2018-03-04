package service;


import library.LibraryService;
import models.Book;
import models.Member;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@WebService(endpointInterface = "service.Library")
public class Library implements LibraryService {

    private static List<Book> books;

    private static List<Member> members;

    public Library() {
        books = Arrays.asList(new Book("Oven attacks", 40, "Drama"),
                new Book("Orange juice", 23, "Comedy"),
                new Book("The notepad", 24, "Fantasy"));
        members = new ArrayList<>();
    }

    @Override
    public List<Book> getAllBooks() {
        return books;
    }

    @Override
    public Book getBookByName(String bookName) {
        for (Book book : books) {
            if (book.getName().equals(bookName)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public List<Book> getBooksCheaperThan(double price) {
        List<Book> cheapBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getPrice() < price) {
                cheapBooks.add(book);
            }
        }
        return cheapBooks;
    }

    @Override
    public Member registerMember(Member member) {
        members.add(member);

        return member;
    }

    @Override
    public Member getMemberByID(int memberID) {
        for (Member member : members) {
            if (member.getMemberID() == memberID) {
                return member;
            }
        }
        return null;
    }

    @Override
    public List<Book> filterByGenre(String genre) {
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getGenre().equals(genre)) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }


}
