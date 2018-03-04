package service;


import library.LibraryService;
import models.Book;
import models.Member;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Library implements LibraryService {

    public static List<Book> books = Arrays.asList(new Book("Oven attacks", 40),
            new Book("Orange juice", 23),
            new Book("The notepad", 24));

    public static List<Member> members = new ArrayList<>();

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

        return null;
    }

    @Override
    public Member registerMember(Member member) {
        members.add(member);

        return member;
    }

    @Override
    public Member getOldestMember(int memberID) {
        return null;
    }
}
