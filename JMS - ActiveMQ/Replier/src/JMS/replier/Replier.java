package JMS.replier;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.*;


public class Replier {


    public void sendReply(String replyMessage, String requestorMessageId, Destination returnAddress) {

        String addressString = returnAddress.toString().substring(returnAddress.toString().lastIndexOf("/") + 1);

        Connection connection; // to connect to the ActiveMQ
        Session session; // session for creating messages, producers and
        Destination sendDestination; // reference to a queue/topic destination
        MessageProducer producer; // for sending messages
        try {
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");

            props.put(("queue." + addressString), addressString);
            Context jndiContext = new InitialContext(props);
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext
                    .lookup("ConnectionFactory");
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Connect to the sender destination
            sendDestination = returnAddress;
            producer = session.createProducer(sendDestination);

//        String body = messageBody; //or serialize an object!
            // Create a text message
            Message msg = session.createTextMessage(replyMessage);
            msg.setJMSCorrelationID(requestorMessageId);
            // Send the message
            producer.send(returnAddress, msg);
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
    }
}
