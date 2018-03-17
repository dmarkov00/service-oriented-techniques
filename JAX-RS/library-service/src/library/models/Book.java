package library.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Book {


    public Book() {

    }

    public Book(int id, String title, String genre, double price) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.price = price;

    }

    private int id;
    private String title;
    private String genre;
    private double price;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "id=" + id + ", title='" + title + ", genre='" + genre + ", price=" + price;
    }
}
