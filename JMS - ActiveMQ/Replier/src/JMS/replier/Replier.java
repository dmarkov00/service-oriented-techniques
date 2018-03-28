package JMS.replier;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class Replier {

    private Connection connection; // to connect to the JMS
    private Session session; // session for creating consumers
    private Destination receiveDestination;    //        reference to a queue/topic destination
    private MessageConsumer consumer; // for receiving messages

    public void sendReply(String replyMessage, int questionId) {
        try {
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");

            props.put(("queue.libraryRequestQueue"), " libraryRequestQueue");
            Context jndiContext = new InitialContext(props);
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext
                    .lookup("ConnectionFactory");
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // Connect to the receiver destination
            receiveDestination = (Destination) jndiContext.lookup("libraryRequestQueue");
            consumer = session.createConsumer(receiveDestination);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message msg) {
                    System.out.println("received: " + msg);
                }
            });
            connection.start(); // this is needed to start receiving messages
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
    }
}
