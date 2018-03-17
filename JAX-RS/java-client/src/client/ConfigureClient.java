package client;

import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class ConfigureClient {

    public static WebTarget configure() {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        URI baseURI = UriBuilder.fromUri("http://localhost:9000/rest/library/books").build();
        
        return client.target(baseURI);
    }
}
