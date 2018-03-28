package JMS.requestor;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Requestor {

    List<Message> answersList = new ArrayList<>();

    public void sendRequest(String messageBody) {
        Connection connection; // to connect to the ActiveMQ
        Session session; // session for creating messages, producers and
        Destination sendDestination; // reference to a queue/topic destination
        MessageProducer producer; // for sending messages
        try {
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");

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
            // After message is sent



        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }

    }

    public void listenForAnswers() {
        Connection connection; // to connect to the JMS
        Session session; // session for creating consumers
        Destination receiveDestination;    //        reference to a queue/topic destination
        MessageConsumer consumer; // for receiving messages
        try {
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");

            props.put(("queue.libraryReplyQueue"), " libraryReplyQueue");
            Context jndiContext = new InitialContext(props);
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext
                    .lookup("ConnectionFactory");
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // Connect to the receiver destination
            receiveDestination = (Destination) jndiContext.lookup("libraryReplyQueue");
            consumer = session.createConsumer(receiveDestination);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message msg) {
                    answersList.add(msg);

                }
            });
            connection.start(); // this is needed to start receiving messages
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
    }
}
