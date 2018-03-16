package client;

import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class Main {

    public static void main(String[] args) {

        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        URI baseURI = UriBuilder.fromUri("http://localhost:8080/rest").build();

        WebTarget serviceTarget = client.target(baseURI);


        // Connect to your operation
        WebTarget operationTarget = serviceTarget.path("simple").path("hello");

        // build the request: media type plain text
        Invocation.Builder requestBuilder = operationTarget.request().accept(MediaType.TEXT_PLAIN);

        // execute operation get
        Response response = requestBuilder.get();

        // read the result answer from the http response
        String result = response.readEntity(String.class);

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            System.out.println(result);
        } else {
            System.err.println(result);
        }

    }
}