package JMS.requestor;

import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.*;

public class Requestor {

    public Map<String, QuestionAndAnswerData> messageData = new LinkedHashMap<>();


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

            // Create a text message
            Message msg = session.createTextMessage(messageBody);

            // Creating reply destination////////////////////////////////////////////
            Destination receiverDestination; // reference to a queue/topic destination
            Properties props1 = new Properties();
            props1.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props1.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");

            props1.put(("queue." + ReplyQueue.replyQueueName), ReplyQueue.replyQueueName);
            Context jndiContext1 = new InitialContext(props1);

            receiverDestination = (Destination) jndiContext1.lookup(ReplyQueue.replyQueueName);
            //////////////////////////////

            // Set reply address to message
            msg.setJMSReplyTo(receiverDestination);

            // Send the message
            producer.send(msg);

            // Retrieve message id
            String messageId = msg.getJMSMessageID();
            // Get the actual message text
            String requestorQuestion = ((ActiveMQTextMessage) msg).getText();

            QuestionAndAnswerData qAndAData = new QuestionAndAnswerData(requestorQuestion);

            // Fill the map for later reference
            messageData.put(messageId, qAndAData);


        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }

    }

}
