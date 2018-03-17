package client;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import java.util.List;
import java.util.Scanner;

import library.models.Book;

public class ServiceManager {
    private Scanner scan = new Scanner(System.in);

    // Link the library service
    private WebTarget serviceTarget = ConfigureClient.configure();


    public void manageInput(String input) {

        switch (input) {
            case "1":
                getBooks();

                break;

            case "2":


                break;
            case "3":


                break;
            case "4":


                break;
            case "5":


                break;
            case "6":


                break;
            case "":
                ConsoleVisualizer.printInstructions();

                break;

            default:
                System.out.println();
                System.out.println("Can not recognize command. Try again.");
        }
    }

    private List<Book> getBooks() {


        WebTarget operationTarget = serviceTarget;

        // build the request: media type plain text
        Invocation.Builder requestBuilder = operationTarget.request().accept(MediaType.APPLICATION_JSON);

        // execute operation get
        Response response = requestBuilder.get();

        // read the result answer from the http response
        String result = response.readEntity(String.class);

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            System.out.println(result);
        } else {
            System.err.println(result);
        }
        return null;
    }
}
