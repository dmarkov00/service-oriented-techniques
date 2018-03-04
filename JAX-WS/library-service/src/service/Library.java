package service;


import library.LibraryService;
import models.Book;
import models.Member;

import java.util.ArrayList;
import java.util.List;

public class Library implements LibraryService {

    Library(){

    }
    List<Book> books;

    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public Book getBookByName(String bookName) {
        return null;
    }

    @Override
    public List<Book> getBooksCheaperThan(double price) {
        return null;
    }

    @Override
    public Member registerMember(Member member) {
        return null;
    }

    @Override
    public Member getMemberByID(int memberID) {
        return null;
    }
}
