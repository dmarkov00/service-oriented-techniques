package models;


public class Book {

    private String name;
    private String genre;
    private double price;

    public Book() {

    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
