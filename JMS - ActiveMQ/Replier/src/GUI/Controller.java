package GUI;


import JMS.replier.QuestionAndAnswerData;
import JMS.replier.Replier;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.*;

public class Controller {
    @FXML
    public ListView<String> messagesListView;
    @FXML
    public TextField messageField;
    private Replier replier = new Replier();

    private Map<String, QuestionAndAnswerData> messageData = new HashMap<>();

    public void buttonSendClicker() {
        String messageBody = messageField.getText();
        replier.sendReply(messageBody, 5);

    }


    @FXML
    public void initialize() {
        this.listenForQuestions();

    }

    private void listenForQuestions() {
        Connection connection; // to connect to the JMS
        Session session; // session for creating consumers
        Destination receiveDestination;    //        reference to a queue/topic destination
        MessageConsumer consumer; // for receiving messages
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
                    String messageId = null;
                    String requestorQuestion = null;
                    try {
                        messageId = msg.getJMSMessageID();
                        requestorQuestion = ((ActiveMQTextMessage) msg).getText();

                    } catch (JMSException e) {
                        e.printStackTrace();
                    }

                    QuestionAndAnswerData qAndAData = new QuestionAndAnswerData(requestorQuestion);

                    messageData.put(messageId, qAndAData);


                    messagesListView.getItems().add(qAndAData.toString());

                }
            });
            connection.start(); // this is needed to start receiving messages
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
    }
}
