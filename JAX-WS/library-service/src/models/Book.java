package models;

import java.io.Serializable;

public class Book implements Serializable {

    private String name;
    private String genre;
    private double price;

    public Book(String name, double price, String genre) {
        this.name = name;
        this.price = price;
        this.genre = genre;
    }


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getGenre() {
        return genre;
    }
}