package library.models;

import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement
public class Book {


    public Book() {

    }

    public Book(String title, String genre, double price) {
        idCount++;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.id = idCount;
    }

    private String title;
    private String genre;
    private double price;
    private int id;
    private static int idCount;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
