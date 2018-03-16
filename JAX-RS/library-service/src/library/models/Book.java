package library.models;

public class Book {


    public Book() {

    }

    public Book(String title, String genre, double price) {
        this.title = title;
        this.genre = genre;
        this.price = price;
        id++;
    }

    private String title;
    private String genre;
    private double price;
    private static int id;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Book.id = id;
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
