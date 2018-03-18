package library.service.resources;

import library.models.Book;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("library")
@Singleton
public class LibraryResources {

    public LibraryResources() {
        books = new ArrayList<>(Arrays.asList(
                new Book(1, "Ghost", "Horror", 96),
                new Book(2, "Pirate", "Comedy", 100),
                new Book(3, "The ship", "Adventure", 21.5),
                new Book(4, "Dogs", "Drama", 22.5)));
    }

    private List<Book> books;
    private List<String> bannedPeople = new ArrayList<>();

    @POST
    @Path("books/ban/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void addPersonToBannedFromLibraryList(@FormParam("name") String name) {
        bannedPeople.add(name);
    }

    @GET()
    @Path("books")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllBooks(@QueryParam("genre") String genre, @QueryParam("price") double price) {
        List<Book> booksResult = this.books;
        if (genre != null) {
            booksResult = filterBooksByGenre(genre, booksResult);
        }
        if (price != 0.0d) {
            booksResult = filterBooksByPrice(price, booksResult);
        }

        GenericEntity<List<Book>> entity = new GenericEntity<List<Book>>(booksResult) {
        };

        return Response.ok(entity).build();
    }

    @POST
    @Path("books")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addBook(Book book) {
        Book bookValidation = getBookWithId(book.getId());
        if (bookValidation == null) {
            this.books.add(book);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Book with such id already exists").build();
        }
    }

    @GET()
    @Path("books/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getBookById(@PathParam("id") int id) {
        Book book = getBookWithId(id);
        if (book != null) {
            return Response.ok(book).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Book with such id does not exist").build();
        }
    }

    @PUT
    @Path("books/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateBookById(@PathParam("id") int id, Book updatedBook) {
        Book book = getBookWithId(id);
        if (book != null) {
            int bookIndex = getBookIndex(book);
            if (bookIndex > 0) {
                books.set(bookIndex, updatedBook);
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Service failure while getting book index!").build();
            }
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Book with such id does not exist").build();
        }
    }

    @DELETE
    @Path("books/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteBookById(@PathParam("id") int id) {
        Book book = getBookWithId(id);
        if (book != null) {
            books.remove(book);
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Book with such id does not exist").build();
        }
        return Response.noContent().build();
    }


    private List<Book> filterBooksByGenre(String genre, List<Book> books) {

        List<Book> filteredBooksByGenre = new ArrayList<>();
        for (Book book : books) {
            if (book.getGenre().toLowerCase().equals(genre.toLowerCase())) {
                filteredBooksByGenre.add(book);
            }
        }
        return filteredBooksByGenre;
    }

    private List<Book> filterBooksByPrice(double price, List<Book> books) {

        List<Book> filteredBooksByPrice = new ArrayList<>();
        for (Book book : books) {
            if (book.getPrice() < price) {
                filteredBooksByPrice.add(book);
            }
        }
        return filteredBooksByPrice;
    }

    private Book getBookWithId(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    private int getBookIndex(Book book) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == book.getId()) {
                return i;
            }
        }
        return -1;
    }

}
