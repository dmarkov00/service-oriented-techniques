package JMS.requestor;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class Requestor {
    private Connection connection; // to connect to the ActiveMQ
    private Session session; // session for creating messages, producers and
    private Destination sendDestination; // reference to a queue/topic destination
    private MessageProducer producer; // for sending messages

    public void sendRequest(String messageBody) {
        try {
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                    "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");


            // connect to the Destination called “myFirstChannel”
            // queue or topic: “queue.myFirstDestination” or “topic.myFirstDestination”

            props.put(("queue.libraryRequestQueue"), "libraryRequestQueue");
            Context jndiContext = new InitialContext(props);
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext
                    .lookup("ConnectionFactory");
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Connect to the sender destination
            sendDestination = (Destination) jndiContext.lookup("libraryRequestQueue");
            producer = session.createProducer(sendDestination);

//        String body = messageBody; //or serialize an object!

            // Create a text message
            Message msg = session.createTextMessage(messageBody);

            // Send the message
            producer.send(msg);
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }

    }
}
